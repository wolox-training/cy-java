package wolox.training.models;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Book {

    public Book() {
    }

    public Book(String genre, String author, String image, String title, String subtitle,
        String publisher, String year, int pages, String isbn,
        List<User> users) {
        this.genre = genre;
        this.author = author;
        this.image = image;
        this.title = title;
        this.subtitle = subtitle;
        this.publisher = publisher;
        this.year = year;
        this.pages = pages;
        this.isbn = isbn;
        this.users = users;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String genre;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String subtitle;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private String year;

    @Column(nullable = false)
    private int pages;

    @Column(nullable = false)
    private String isbn;

    @ManyToMany(mappedBy = "books")
    private List<User> users = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = Preconditions.checkNotNull(author, "The author can't be null");
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = Preconditions.checkNotNull(image, "The image can't be null");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = Preconditions.checkNotNull(title, "The title can't be null");
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = Preconditions.checkNotNull(subtitle, "The subtitle can't be null");
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = Preconditions.checkNotNull(publisher, "The publisher can't be null");
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = Preconditions.checkNotNull(year, "The year can't be null");
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = Preconditions.checkNotNull(pages, "The pages can't be null");
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = Preconditions.checkNotNull(isbn, "The isbn can't be null");
    }

    public List<User> getUsers() {
        return (List<User>) Collections.unmodifiableCollection(users);
    }

    public void setUsers(List<User> users) {
        Preconditions.checkArgument(users != null && !users.isEmpty(),
            "The users cant be null or empty");
        this.users = users;
    }
}
