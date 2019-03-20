package wolox.training.repositories;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

//    @Autowired
//    private BookRepository bookRepository;
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Test
//    public void findByAuthorTest() {
//        Book book = new Book();
//        book.setGenre("Comedy Test");
//        book.setAuthor("Cristian Test");
//        book.setImage("Image/Test.jpg");
//        book.setTitle("The test");
//        book.setSubtitle("JustTestSub");
//        book.setPublisher("TestPublisher");
//        book.setYear("2019");
//        book.setPages(100);
//        book.setIsbn("AV100Test");
//
//        entityManager.persistAndFlush(book);
//        Book bookTest = bookRepository.findByAuthor(book.getAuthor());
//        assertThat(book.getAuthor()).isEqualTo(bookTest.getAuthor());
//    }
//
//    @Test
//    public void findByTitleTest() {
//        Book book = new Book();
//        book.setGenre("Comedy Test");
//        book.setAuthor("Cristian Test");
//        book.setImage("Image/Test.jpg");
//        book.setTitle("The Title test");
//        book.setSubtitle("JustTestSub");
//        book.setPublisher("TestPublisher");
//        book.setYear("2019");
//        book.setPages(100);
//        book.setIsbn("AV100Test");
//
//        entityManager.persistAndFlush(book);
//        Book bookTest = bookRepository.findByTitle(book.getTitle());
//        assertThat(book.getTitle()).isEqualTo(bookTest.getTitle());
//    }
//
//    @Test
//    public void findAllTest() {
//        Book book1 = new Book();
//        book1.setGenre("Comedy Test");
//        book1.setAuthor("Cristian Test");
//        book1.setImage("Image/Test.jpg");
//        book1.setTitle("The Title test");
//        book1.setSubtitle("JustTestSub");
//        book1.setPublisher("TestPublisher");
//        book1.setYear("2019");
//        book1.setPages(100);
//        book1.setIsbn("AV100Test");
//
//        Book book2 = new Book();
//        book2.setGenre("Comedy Test2");
//        book2.setAuthor("Cristian Test2");
//        book2.setImage("Image/Test.jpg2");
//        book2.setTitle("The Title test2");
//        book2.setSubtitle("JustTestSub2");
//        book2.setPublisher("TestPublisher2");
//        book2.setYear("20192");
//        book2.setPages(1002);
//        book2.setIsbn("AV100Test2");
//
//        List<Book> books = new ArrayList<>();
//        books.add(book1);
//        books.add(book2);
//
//        for(Book book : books) {
//            entityManager.persistAndFlush(book);
//        }
//
//        List<Book> booksTest = bookRepository.findAll();
//        assertThat(booksTest.size()).isEqualTo(5);
//    }
//
//    @Test
//    public void createTest() {
//        Book book = new Book();
//        book.setGenre("Comedy Test");
//        book.setAuthor("Cristian Test");
//        book.setImage("Image/Test.jpg");
//        book.setTitle("The Title test");
//        book.setSubtitle("JustTestSub");
//        book.setPublisher("TestPublisher");
//        book.setYear("2019");
//        book.setPages(100);
//        book.setIsbn("AV100Test");
//        Book createdBook = bookRepository.save(book);
//        assertThat(book).isEqualTo(createdBook);
//    }
//
//    @Test
//    public void findByIdTest() {
//        Book book = new Book();
//        book.setGenre("Comedy Test");
//        book.setAuthor("Cristian Test");
//        book.setImage("Image/Test.jpg");
//        book.setTitle("The Title test");
//        book.setSubtitle("JustTestSub");
//        book.setPublisher("TestPublisher");
//        book.setYear("2019");
//        book.setPages(100);
//        book.setIsbn("AV100Test");
//        entityManager.persistAndFlush(book);
//        Book bookTest = bookRepository.findById(book.getId()).get();
//        assertThat(bookTest).isEqualTo(book);
//    }
//
//    @Test
//    public void notFoundFindByTitleTest() {
//        Book book = new Book();
//        book.setGenre("Comedy Test");
//        book.setAuthor("Cristian Test");
//        book.setImage("Image/Test.jpg");
//        book.setTitle("The Title test");
//        book.setSubtitle("JustTestSub");
//        book.setPublisher("TestPublisher");
//        book.setYear("2019");
//        book.setPages(100);
//        book.setIsbn("AV100Test");
//        entityManager.persistAndFlush(book);
//        Book bookTest = bookRepository.findByTitle("No exist title");
//        assertThat(bookTest).isNotEqualTo(book);
//    }
//
//    @Test
//    public void notFoundFindByAuthorTest() {
//        Book book = new Book();
//        book.setGenre("Comedy Test");
//        book.setAuthor("Cristian Test");
//        book.setImage("Image/Test.jpg");
//        book.setTitle("The Title test");
//        book.setSubtitle("JustTestSub");
//        book.setPublisher("TestPublisher");
//        book.setYear("2019");
//        book.setPages(100);
//        book.setIsbn("AV100Test");
//        entityManager.persistAndFlush(book);
//        Book bookTest = bookRepository.findByAuthor("Not exist author");
//        assertThat(bookTest).isNotEqualTo(book);
//    }
//
//    @Test(expected = NoSuchElementException.class)
//    public void notFoundFindByIdTest() {
//        Book book = new Book();
//        book.setGenre("Comedy Test");
//        book.setAuthor("Cristian Test");
//        book.setImage("Image/Test.jpg");
//        book.setTitle("The Title test");
//        book.setSubtitle("JustTestSub");
//        book.setPublisher("TestPublisher");
//        book.setYear("2019");
//        book.setPages(100);
//        book.setIsbn("AV100Test");
//        entityManager.persistAndFlush(book);
//        Book bookTest = bookRepository.findById(100L).get();
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void notCreateTest() {
//        Book book = new Book();
//        book.setGenre("Comedy Test");
//        book.setAuthor(null);
//        book.setImage(null);
//        book.setTitle("The Title test");
//        book.setSubtitle("JustTestSub");
//        book.setPublisher("TestPublisher");
//        book.setYear("2019");
//        book.setPages(100);
//        book.setIsbn("AV100Test");
//        bookRepository.save(book);
//    }
}
