package wolox.training.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping
    public Iterable findAll() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book findOne(@PathVariable Long id) {
        return bookRepository.findById(id)
            .orElseThrow(() -> new BookNotFoundException("Book not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookRepository.findById(id)
            .orElseThrow(() -> new BookNotFoundException("Book not found"));
        bookRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
        if (book.getId() != id) {
            throw new BookIdMismatchException("Book id mismatch");
        }
        bookRepository.findById(id)
            .orElseThrow(() -> new BookNotFoundException("Book not found"));
        return bookRepository.save(book);
    }

    @GetMapping("/title/{bookTitle}")
    public Book findByTitle(@PathVariable String bookTitle) {
        return bookRepository.findByTitle(bookTitle);
    }

    @GetMapping("isbn/{isbn}")
    public ResponseEntity<Book> findByIsbn(@PathVariable String isbn) {
        Book requestedBook = bookRepository.findByIsbn(isbn);
        if (requestedBook == null) {
            OpenLibraryService openLibraryService = new OpenLibraryService();
            requestedBook = openLibraryService.bookInfo(isbn);
            if (requestedBook == null) {
                return new ResponseEntity(
                    System.getenv("There is no book with that ISBN"),
                    HttpStatus.BAD_REQUEST);
            }
            bookRepository.save(requestedBook);
        }
        return new ResponseEntity<>(requestedBook, HttpStatus.OK);
    }

    @GetMapping("/matcheencomplex")
    public List<Book> findByPublisherAndGenreAndYear(
        @RequestParam("publisher") String publisher,
        @RequestParam("genre") String genre,
        @RequestParam("year") String year) {

        List<Book> books = bookRepository.findByPublisherAndGenreAndYear(publisher, genre, year);

        return books;
    }

    @GetMapping("/getall")
    public List<Book> getAll(
        @RequestParam(value = "genre", defaultValue = "Scary") String genre,
        @RequestParam(value = "author", defaultValue = "Cristian") String author,
        @RequestParam(value = "image", defaultValue = "Scary.jpg") String image,
        @RequestParam(value = "title", defaultValue = "Scary Movie") String title,
        @RequestParam(value = "subtitle", defaultValue = "Scary Movie 2") String subtitle,
        @RequestParam("publisher") String publisher,
        @RequestParam(value = "year", defaultValue = "2019") String year,
        @RequestParam(value = "pages", defaultValue = "1") Integer pages,
        @RequestParam("isbn") String isbn) {

        List<Book> books = bookRepository.getAll(genre, author, image, title, subtitle,
            publisher, year, pages, isbn);

        return books;
    }

}
