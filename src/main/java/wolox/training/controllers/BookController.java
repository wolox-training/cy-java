package wolox.training.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import wolox.training.exceptions.BookIdMismatchException;
import wolox.training.exceptions.BookNotFoundException;
import wolox.training.models.Book;
import wolox.training.repositories.BookRepository;
import wolox.training.services.OpenLibraryService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @GetMapping("/{id}")
    @ResponseBody
    public Mono<Book> findOne(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return Mono.defer(() -> Mono.just(book.get()));
        } else {
            throw new BookNotFoundException("Book not found");
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Book> create(@RequestBody Book book) {
        return Mono
            .fromCallable(() -> transactionTemplate.execute(status -> bookRepository.save(book)));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new BookNotFoundException("Book not found"));
        bookRepository.deleteById(id);
        return Mono.just(book).then();
    }

    @PutMapping("/{id}")
    public Mono<Book> updateBook(@RequestBody Book book, @PathVariable Long id) {
        if (book.getId() != id) {
            throw new BookIdMismatchException("Book id mismatch");
        }
        bookRepository.findById(id)
            .orElseThrow(() -> new BookNotFoundException("Book not found"));
        return Mono.just(bookRepository.save(book));
    }

    @GetMapping("/title/{bookTitle}")
    public Flux<Book> findByTitle(@PathVariable String bookTitle, Pageable pageable) {
        Page<Book> books = bookRepository.findByTitle(bookTitle, pageable);
        Flux<Book> booksFlux = Flux.defer(
            () -> books.getTotalElements() > 0 ? Flux.fromIterable(books)
                : Flux.error(new BookNotFoundException("Book not found with this title")));
        return booksFlux;
    }

    @GetMapping("isbn/{isbn}")
    public Mono<ResponseEntity<Book>> findByIsbn(@PathVariable String isbn) {
        Book requestedBook = bookRepository.findByIsbn(isbn);
        if (requestedBook == null) {
            OpenLibraryService openLibraryService = new OpenLibraryService();
            requestedBook = openLibraryService.bookInfo(isbn);
            if (requestedBook == null) {
                return Mono
                    .error(new BookNotFoundException("There's no a book with ISBN: " + isbn));
            }
            create(requestedBook);
        }
        return Mono.just(requestedBook).map(b -> new ResponseEntity<>(b, HttpStatus.OK));
    }

    @GetMapping("/matcheencomplex")
    public Flux<Book> findByPublisherAndGenreAndYear(
        @RequestParam("publisher") String publisher,
        @RequestParam("genre") String genre,
        @RequestParam("year") String year,
        Pageable pageable) {

        Page<Book> books = bookRepository
            .findByPublisherAndGenreAndYear(publisher, genre, year, pageable);
        Flux<Book> booksFlux = Flux.defer(
            () -> books.getTotalElements() > 0 ? Flux.fromIterable(books)
                : Flux.error(new BookNotFoundException(
                    "Book not found with this publisher, genre and year")));
        return booksFlux;
    }

    @GetMapping
    public Flux<Book> getAll(
        @RequestParam(value = "genre", defaultValue = "") String genre,
        @RequestParam(value = "author", defaultValue = "") String author,
        @RequestParam(value = "image", defaultValue = "") String image,
        @RequestParam(value = "title", defaultValue = "") String title,
        @RequestParam(value = "subtitle", defaultValue = "") String subtitle,
        @RequestParam(value = "publisher", defaultValue = "") String publisher,
        @RequestParam(value = "year", defaultValue = "") String year,
        @RequestParam(value = "pages", required = false) Integer pages,
        @RequestParam(value = "isbn", defaultValue = "") String isbn,
        Pageable pageable) {

        Page<Book> books = bookRepository.getAll(genre, author, image, title, subtitle,
            publisher, year, pages, isbn, pageable);
        Flux<Book> booksFlux = Flux.defer(
            () -> books.getTotalElements() > 0 ? Flux.fromIterable(books)
                : Flux.fromIterable(bookRepository.findAll()));
        return booksFlux;
    }
}

