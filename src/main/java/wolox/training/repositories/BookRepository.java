package wolox.training.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wolox.training.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByAuthor(String author);

    Book findByTitle(String title);

    Book findByIsbn(String isbn);

    @Query("SELECT b FROM Book b WHERE (b.publisher = ?1 OR b.publisher IS NULL) AND (b.genre = ?2 OR b.genre IS NULL) AND (b.year = ?3 OR b.year IS NULL)")
    List<Book> findByPublisherAndGenreAndYear(String publisher, String genre, String year);
}
