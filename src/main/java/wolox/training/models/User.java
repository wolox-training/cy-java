package wolox.training.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Preconditions;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import wolox.training.exceptions.BookAlreadyOwnedException;
import wolox.training.exceptions.BookNotFoundException;


@Entity
@Table(name = "users")
public class User {

    public User() {
    }

    public User(String username, String name, LocalDate birthdate) {
        setUsername(username);
        setName(name);
        setBirthdate(birthdate);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birthdate;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnore
    private List<Book> books = new ArrayList<>();

    @Column(nullable = false)
    private String password;

    private String role = "USER";

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

    public List<Book> getBooks() {
        return (List<Book>) Collections.unmodifiableCollection(books);
    }

    public void setBooks(List<Book> books) {
        this.books = Preconditions.checkNotNull(books, "The books can't be null");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public boolean validatePassword(String password) {
        return new BCryptPasswordEncoder().matches(password, this.password);
    }
}
