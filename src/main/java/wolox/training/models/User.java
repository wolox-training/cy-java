package wolox.training.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Preconditions;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import wolox.training.exceptions.BookAlreadyOwnedException;
import wolox.training.exceptions.BookNotFoundException;


@Entity
public class User {

    public User() {
    }

    public User(String username, String name, LocalDate birthdate) {
        this.username = username;
        this.name = name;
        this.birthdate = birthdate;
    }

    public User(String username, String name, LocalDate birthdate,
        Collection<Book> books) {
        this.username = username;
        this.name = name;
        this.birthdate = birthdate;
        this.books = books;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birthdate;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnore
    private Collection<Book> books = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        Preconditions.checkArgument(username != null && !username.isEmpty(),
            "The username can't be null or empty");
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Preconditions.checkArgument(name != null && !name.isEmpty(),
            "The name can't be null or empty");
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {

        this.birthdate = Preconditions.checkNotNull(birthdate, "The birthdate can't be null");
    }

    public Collection<Book> getBooks() {
        return Collections.unmodifiableCollection(books);
    }

    public void setBooks(List<Book> books) {
        this.books = Preconditions.checkNotNull(books, "The books can't be null");
    }

    public boolean addBookToUser(Book newBook) {
        Boolean book = books.stream().anyMatch(b -> b.equals(newBook));
        if (book) {
            throw new BookAlreadyOwnedException("Book id mismatch");
        }else {
            return books.add(newBook);
        }
    }

    public void deleteBookFromUser(Book deleteBook) {
        Boolean book = books.stream().anyMatch(b -> b.equals(deleteBook));
        if(book) {
            books.remove(deleteBook);
        }else {
            throw new BookNotFoundException("Book not found");
        }
    }
}
