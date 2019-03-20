package wolox.training.controllers;

//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.Test;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import wolox.training.models.Book;
//import wolox.training.models.User;
//import wolox.training.repositories.BookRepository;
//import wolox.training.repositories.UserRepository;
//import wolox.training.utils.Utils;

//@RunWith(SpringRunner.class)
//@WebMvcTest(UserController.class)
public class UserControllerTest {
//    @Autowired
//    private MockMvc mvc;

//    @MockBean
//    private UserRepository userRepository;
//
//    @MockBean
//    private BookRepository bookRepository;
//
//    @MockBean
//    private User user;
//
//    @MockBean
//    private Book book;

//    @Test
//    public void findAllTest() throws Exception{
//
//        User user = new User();
//        user.setUsername("Anita");
//        user.setName("Ana");
//        user.setBirthdate(LocalDate.now());
//
//        List<User> users = new ArrayList<>();
//        users.add(user);
//
//        given(userRepository.findAll()).willReturn(users);
//
//        mvc.perform(get("/api/users")
//            .contentType(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk())
//            .andExpect(content()
//                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
//    }

//    @Test
//    public void findOneTest() throws Exception {
//        user = new User();
//        user.setUsername("Anita");
//        user.setName("Ana");
//        user.setBirthdate(LocalDate.now());
//        given(userRepository.findById(1l)).willReturn(Optional.of(user));
//        mvc.perform(get("/api/users/{id}", 1l))
//            .andExpect(status().isOk());
//    }

//    @Test
//    public void notFindOneTest() throws Exception {
//        given(userRepository.findById(1l)).willReturn(Optional.of(user));
//        mvc.perform(get("/api/books/{id}", 1l))
//            .andExpect(status().is4xxClientError());
//    }

//    @Test
//    public void createTest() throws Exception {
//        String userJson = "\n{\"username\":\"username1\",\"Anita\":\"Ana\",\"birthDate\":\"2019-12-1\"}";
//        mvc.perform(post("/api/users")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(userJson))
//            .andExpect(status().isCreated());
//    }
//
//    @Test
//    public void notCreateTest() throws Exception {
//        User user = new User();
//        user.setUsername("Anita");
//
//        mvc.perform(post("/api/users")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(Utils.asJsonString(user)))
//            .andExpect(status().is4xxClientError());
//    }

    //    @Test
//    public void deleteTest() throws Exception {
//        given(user.getId()).willReturn(1l);
//        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));
//        mvc.perform(delete("/api/users/{id}", user.getId()))
//            .andExpect(status().isOk());
//    }
//    @Test
//    public void notFoundDeleteTest() throws Exception {
//        mvc.perform(delete("/api/users/{id}", 2l))
//            .andExpect(status().is4xxClientError());
//    }

//    @Test
//    public void updateTest() throws Exception {
//        String userJson = "\n{\"id\": 1,\"username\":\"Anita\",\"name\":\"Ana\",\"birthDate\":\"2019-12-1\"}";
//        given(userRepository.findById(1l)).willReturn(Optional.of(user));
//        mvc.perform(put("/api/users/{id}", 1l)
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(userJson))
//            .andExpect(status().isOk());
//    }

//    @Test
//    public void notUpdateTest() throws Exception {
//        given(user.getId()).willReturn(1l);
//        String  bodyUserJson = "{\"id\":\1\"}";
//        mvc.perform(put("/api/users/{id}", 2l)
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(bodyUserJson))
//            .andExpect(status().is4xxClientError());
//    }
}
