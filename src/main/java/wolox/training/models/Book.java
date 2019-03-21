package wolox.training.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
        String publisher, String year, int pages, String isbn) {
        setGenre(genre);
        setAuthor(author);
        setImage(image);
        setTitle(title);
        setSubtitle(subtitle);
        setPublisher(publisher);
        setYear(year);
        setPages(pages);
        setIsbn(isbn);
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
    @JsonIgnore
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
        Preconditions.checkArgument(author != null && !author.isEmpty(),
            "The author can't be null or empty");
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        Preconditions.checkArgument(image != null && !image.isEmpty(),
            "The image can't be null or empty");
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        Preconditions.checkArgument(title != null && !title.isEmpty(),
            "The title can't be null or empty");
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        Preconditions.checkArgument(subtitle != null && !subtitle.isEmpty(),
            "The subtitle can't be null or empty");
        this.subtitle = subtitle;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        Preconditions.checkArgument(publisher != null && !publisher.isEmpty(),
            "The publisher can't be null or empty");
        this.publisher = publisher;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        Preconditions.checkArgument(year != null && !year.isEmpty(),
            "The year can't be null or empty");
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        Preconditions.checkArgument(pages != null && pages > 0,
            "The pages can't be null or less than 0");
        this.pages = pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        Preconditions.checkArgument(isbn != null && !isbn.isEmpty(),
            "The isbn can't be null or empty");
        this.isbn = isbn;
    }

    public List<User> getUsers() {
        return (List<User>) Collections.unmodifiableCollection(users);
    }

    public void setUsers(List<User> users) {
        this.users = Preconditions.checkNotNull(users, "The users can't be null");
    }
}
