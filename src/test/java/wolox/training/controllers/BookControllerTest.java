package wolox.training.controllers;

//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import wolox.training.models.Book;
//import wolox.training.repositories.BookRepository;
//import wolox.training.utils.Utils;

//@RunWith(SpringRunner.class)
//@WebMvcTest(BookController.class)
public class BookControllerTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private BookRepository bookRepository;
//
//    @MockBean
//    private Book book;
//
//    @Test
//    public void findAllTest() throws Exception {
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
//        Book book2 = new Book();
//        book2.setGenre("Comedy Test");
//        book2.setAuthor("Cristian Test");
//        book2.setImage("Image/Test.jpg");
//        book2.setTitle("The Title test");
//        book2.setSubtitle("JustTestSub");
//        book2.setPublisher("TestPublisher");
//        book2.setYear("2019");
//        book2.setPages(100);
//        book2.setIsbn("AV100Test");
//
//        List<Book> books = new ArrayList<>();
//        books.add(book);
//        books.add(book2);
//
//        given(bookRepository.findAll()).willReturn(books);
//        mvc.perform(get("/api/books")
//            .contentType(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk())
//            .andExpect(content()
//                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    public void findOneTest() throws Exception {
//        book = new Book();
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
//        given(bookRepository.findById(1l)).willReturn(Optional.of(book));
//        mvc.perform(get("/api/books/{id}", 1l))
//            .andExpect(status().isOk());
//    }
//
//    @Test
//    public void NotFoundFindOneTest() throws Exception {
//        mvc.perform(get("/api/books/{id}", 1l))
//            .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void createTest() throws Exception {
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
//        mvc.perform(post("/api/books")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(Utils.asJsonString(book)))
//            .andExpect(status().isCreated());
//    }
//
//    @Test
//    public void notCreateBookTest() throws Exception {
//        Book book = new Book();
//        book.setTitle("title");
//
//        mvc.perform(post("/api/books")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(Utils.asJsonString(book)))
//            .andExpect(status().is4xxClientError());
//    }
//
//    @Test
//    public void deleteBookTest() throws Exception {
//        given(book.getId()).willReturn(1l);
//        given(bookRepository.findById(book.getId())).willReturn(Optional.of(book));
//        mvc.perform(delete("/api/books/{id}", book.getId()))
//            .andExpect(status().isOk());
//    }
//
//    @Test
//    public void notFoundDeleteOneTest() throws Exception {
//        mvc.perform(delete("/api/books/{id}", 2l))
//            .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void updateBookTest() throws Exception {
//        String bookJson = "{\n\t\"id\": 1,\n\t\"genre\": \"Comedy Test\",\n\t\"author\": \"Cristian Test\",\n\t\"image\": \"Image/Test.jpg\",\n\t\"title\": \"The Title test\",\n\t\"subtitle\": \"JustTestSub\",\n\t\"publisher\": \"TestPublisher\",\n\t\"year\":\"2019\",\n\t\"pages\":100,\n\t\"isbn\":\"AV100Test\"\n}";
//        given(bookRepository.findById(1l)).willReturn(Optional.of(book));
//        mvc.perform(put("/api/books/{id}", 1l)
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(bookJson))
//            .andExpect(status().isOk());
//    }
//
//    @Test
//    public void notUpdateTest() throws Exception {
//        given(book.getId()).willReturn(1l);
//        String bookJson = "{\"id\":\1\"}";
//        mvc.perform(put("/api/books/{id}", 2l)
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(bookJson))
//            .andExpect(status().is4xxClientError());
//
//    }
}
