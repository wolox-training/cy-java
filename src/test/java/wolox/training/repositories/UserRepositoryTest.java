package wolox.training.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import wolox.training.models.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findByUsernameTest() {
        User user = new User();
        user.setUsername("Cristian Test");
        user.setName("Daniel Test");
        user.setBirthdate(LocalDate.now());
        entityManager.persistAndFlush(user);
        User userTest = userRepository.findByUsername(user.getUsername());
        assertThat(user.getUsername()).isEqualTo(userTest.getUsername());
    }

    @Test
    public void findAllTest() {
        User user1 = new User();
        user1.setUsername("Cristian Test");
        user1.setName("Daniel Test");
        user1.setBirthdate(LocalDate.now());

        User user2 = new User();
        user2.setUsername("Cristian Test2");
        user2.setName("Daniel Test2");
        user2.setBirthdate(LocalDate.now());

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        for(User user : users) {
            entityManager.persistAndFlush(user);
        }

        List<User> usersTest = userRepository.findAll();
        assertThat(usersTest.size()).isEqualTo(3);
    }

    @Test
    public void createTest() {
        User user = new User();
        user.setUsername("Cristian Test");
        user.setName("Daniel Test");
        user.setBirthdate(LocalDate.now());
        User createdUser = userRepository.save(user);
        assertThat(user).isEqualTo(createdUser);
    }

    @Test
    public void findByIdTest() {
        User user = new User();
        user.setUsername("Cristian Test");
        user.setName("Daniel Test");
        user.setBirthdate(LocalDate.now());
        entityManager.persistAndFlush(user);
        User userTest = userRepository.findById(user.getId()).get();
        assertThat(userTest).isEqualTo(user);
    }

    @Test
    public void notFoundFindByUsernameTest() {
        User user = new User();
        user.setUsername("Cristian Test");
        user.setName("Daniel Test");
        user.setBirthdate(LocalDate.now());
        entityManager.persistAndFlush(user);
        User userTest = userRepository.findByUsername("No exist username");
        assertThat(userTest).isNotEqualTo(user);
    }

    @Test(expected = NoSuchElementException.class)
    public void notFoundFindByIdTest() {
        User user = new User();
        user.setUsername("Cristian Test");
        user.setName("Daniel Test");
        user.setBirthdate(LocalDate.now());
        entityManager.persistAndFlush(user);
        User userTest = userRepository.findById(100L).get();
    }

    @Test(expected = IllegalArgumentException.class)
    public void notCreateTest() {
        User user = new User();
        user.setUsername("Cristian Test");
        user.setName(null);
        user.setBirthdate(null);
        userRepository.save(user);
    }

}
