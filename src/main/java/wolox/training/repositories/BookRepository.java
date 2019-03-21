package wolox.training.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wolox.training.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findByAuthor(String author, Pageable pageable);

    Page<Book> findByTitle(String title, Pageable pageable);

    Book findByIsbn(String isbn);

    @Query("SELECT b FROM Book b WHERE (b.publisher LIKE %:publisher% OR b.publisher IS NULL) AND (b.genre LIKE %:genre% OR b.genre IS NULL) AND (b.year LIKE %:year% OR b.year IS NULL)")
    Page<Book> findByPublisherAndGenreAndYear(@Param("publisher") String publisher,
        @Param("genre") String genre, @Param("year") String year,
        Pageable pageable);

    @Query(
        "SELECT b FROM Book b WHERE (b.genre LIKE %:genre% OR b.genre IS NULL) OR (b.author LIKE %:author% OR b.author IS NULL) "
            +
            "OR (b.image LIKE %:image% OR b.image IS NULL) OR (b.title LIKE %:title% OR b.title IS NULL)"
            +
            "OR (b.subtitle LIKE %:subtitle% OR b.subtitle IS NULL) OR (b.publisher LIKE %:publisher% OR b.publisher IS NULL)"
            +
            "OR (b.year LIKE %:year% OR b.year IS NULL) OR (b.pages = :pages OR b.pages IS NULL)"
            +
            "OR (b.isbn LIKE %:isbn% OR b.isbn IS NULL)")
    Page<Book> getAll(@Param("genre") String genre, @Param("author") String author,
        @Param("image") String image, @Param("title") String title,
        @Param("subtitle") String subtitle,
        @Param("publisher") String publisher, @Param("year") String year,
        @Param("pages") Integer pages, @Param("isbn") String isbn,
        Pageable pageable);
}
