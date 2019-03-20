package wolox.training.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import wolox.training.models.User;
import wolox.training.repositories.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private BookRepository bookRepository;

    @GetMapping(value = "/{id}", name = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<User> findOne(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @GetMapping
    public Flux<User> findAll() {
        return userRepository.findAll();
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public User create(@RequestBody User user) {
//        return userRepository.save(user);
//    }

//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        userRepository.findById(id)
//            .orElseThrow(() -> new UserNotFoundException("User not found"));
//        userRepository.deleteById(id);
//    }

//    @PutMapping("/{id}")
//    public User updateUser(@RequestBody User user, @PathVariable Long id) {
//        if (user.getId() != id) {
//            throw new UserIdMismatchException("User id mismatch");
//        }
//        userRepository.findById(id)
//            .orElseThrow(() -> new UserNotFoundException("User not found"));
//        return userRepository.save(user);
//    }

    @GetMapping("/username/{userUsername}")
    public User findByUsername(@PathVariable String userUsername) {
        return userRepository.findByUsername(userUsername);
    }

//    @PostMapping("/{userId}/addBook/{bookId}")
//    public void addBookToUser(@PathVariable Long userId, @PathVariable Long bookId) {
//        Book bookAdd = bookRepository.findById(bookId)
//            .orElseThrow(() -> new BookNotFoundException("Book ot found"));
//        User user = userRepository.findById(userId)
//            .orElseThrow(() -> new UserNotFoundException("User not found"));
//        user.addBookToUser(bookAdd);
//    }

//    @PostMapping("{userId}/deleteBook/{bookId}")
//    public void deleteBookFromUser(@PathVariable Long userId, @PathVariable Long bookId) {
//        Book book = bookRepository.findById(bookId)
//            .orElseThrow(() -> new BookNotFoundException("Book ot found"));
//        User user = userRepository.findById(userId)
//            .orElseThrow(() -> new UserNotFoundException("User not found"));
//        user.deleteBookFromUser(book);
//    }

//    @RequestMapping(value = "/username", method = RequestMethod.GET)
//    @ResponseBody
//    public User currentUsername(Principal principal) {
//        return userRepository.findByUsername(principal.getName());
//    }

//    @GetMapping("/matcheencomplex")
//    public Page<User> findByBirthdateBetweenAndUsernameContainingIgnoreCase(
//        @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
//        @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
//        @RequestParam(name = "username", defaultValue = "") String username,
//        Pageable pageable) {
//
//        return userRepository
//            .findByBirthdateBetweenAndUsernameContainingIgnoreCase(startDate, endDate, username,
//                pageable);
//    }

//    @GetMapping
//    public Page<User> getAll(
//        @RequestParam(value = "username", defaultValue = "") String username,
//        @RequestParam(value = "name", defaultValue = "") String name,
//        @RequestParam(value = "birthdate", required = false) LocalDate birthdate,
//        Pageable pageable) {
//
//        Page<User> users = userRepository.getAll(username, name, birthdate, pageable);
//
//        return users;
//    }
}
