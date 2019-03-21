package wolox.training.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import wolox.training.exceptions.BookNotFoundException;
import wolox.training.exceptions.UserIdMismatchException;
import wolox.training.exceptions.UserNotFoundException;
import wolox.training.models.Book;
import wolox.training.models.User;
import wolox.training.repositories.BookRepository;
import wolox.training.repositories.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/{id}")
    public Mono<User> findOne(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return Mono.defer(() -> Mono.just(user.get()));
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> create(@RequestBody User user) {
        return Mono
            .fromCallable(() -> transactionTemplate.execute(status -> userRepository.save(user)));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.deleteById(id);
        return Mono.just(user).then();
    }

    @PutMapping("/{id}")
    public Mono<User> updateUser(@RequestBody User user, @PathVariable Long id) {
        if (user.getId() != id) {
            throw new UserIdMismatchException("User id mismatch");
        }
        userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        return Mono.just(userRepository.save(user));
    }

    @GetMapping("/username/{userUsername}")
    public Mono<User> findByUsername(@PathVariable String userUsername) {
        User user = userRepository.findByUsername(userUsername);
        return user != null ? Mono.just(user)
            : Mono.error(new UserNotFoundException("User not found with this username"));
    }

    @PostMapping("/{userId}/addBook/{bookId}")
    public Mono<Void> addBookToUser(@PathVariable Long userId, @PathVariable Long bookId) {
        Book bookAdd = bookRepository.findById(bookId)
            .orElseThrow(() -> new BookNotFoundException("Book not found"));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.addBookToUser(bookAdd);
        updateUser(user, userId);
        return Mono.just(user).then();
    }

    @PostMapping("{userId}/deleteBook/{bookId}")
    public Mono<Void> deleteBookFromUser(@PathVariable Long userId, @PathVariable Long bookId) {
        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new BookNotFoundException("Book ot found"));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.deleteBookFromUser(book);
        updateUser(user, userId);
        return Mono.just(user).then();
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public Mono<User> currentUsername(Principal principal) {
        return Mono.just(userRepository.findByUsername(principal.getName()));
    }

    @GetMapping("/matcheencomplex")
    public Flux<User> findByBirthdateBetweenAndUsernameContainingIgnoreCase(
        @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
        @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
        @RequestParam(name = "username", defaultValue = "") String username,
        Pageable pageable) {
        Page<User> users = userRepository
            .findByBirthdateBetweenAndUsernameContainingIgnoreCase(startDate, endDate, username,
                pageable);
        Flux<User> usersFlux = Flux.defer(
            () -> users.getTotalElements() > 0 ? Flux.fromIterable(users)
                : Flux.error(new UserNotFoundException(
                    "User not found between these birthdates and username")));
        return usersFlux;
    }

    @GetMapping
    public Flux<User> getAll(
        @RequestParam(value = "username", defaultValue = "") String username,
        @RequestParam(value = "name", defaultValue = "") String name,
        @RequestParam(value = "birthdate", required = false) LocalDate birthdate,
        Pageable pageable) {

        Page<User> users = userRepository.getAll(username, name, birthdate, pageable);
        Flux<User> usersFlux = Flux.defer(
            () -> users.getTotalElements() > 0 ? Flux.fromIterable(users)
                : Flux.fromIterable(userRepository.findAll()));
        return usersFlux;
    }
}
