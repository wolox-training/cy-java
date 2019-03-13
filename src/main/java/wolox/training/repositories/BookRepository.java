package wolox.training.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wolox.training.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByAuthor(String author);

    Book findByTitle(String title);

    Book findByIsbn(String isbn);

    @Query("SELECT b FROM Book b WHERE (b.publisher = :publisher OR b.publisher IS NULL) AND (b.genre = :genre OR b.genre IS NULL) AND (b.year = :year OR b.year IS NULL)")
    Page<Book> findByPublisherAndGenreAndYear(String publisher, String genre, String year,
        Pageable pageable);

    @Query(
        "SELECT b FROM Book b WHERE (b.genre LIKE %:genre% OR b.genre IS NULL) AND (b.author LIKE %:author% OR b.author IS NULL) "
            +
            "AND (b.image LIKE %:image% OR b.image IS NULL) AND (b.title LIKE %:title% OR b.title IS NULL)"
            +
            "AND (b.subtitle LIKE %:subtitle% OR b.subtitle IS NULL) AND (b.publisher LIKE %:publisher% OR b.publisher IS NULL)"
            +
            "AND (b.year LIKE %:year% OR b.year IS NULL) AND (b.pages = :pages OR b.pages IS NULL)"
            +
            "AND (b.isbn LIKE %:isbn% OR b.isbn IS NULL)")
    Page<Book> getAll(String genre, String author, String image, String title, String subtitle,
        String publisher, String year, Integer pages, String isbn, Pageable pageable);
}
