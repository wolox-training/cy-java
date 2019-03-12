package wolox.training.repositories;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wolox.training.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query(
        "SELECT u FROM User u WHERE ((u.birthdate > :startDate AND u.birthdate < :endDate) OR u.birthdate IS NULL) "
            + "AND (u.username = :username OR u.username IS NULL)")
    List<User> findByBirthdateBetweenAndUsernameContainingIgnoreCase(LocalDate startDate,
        LocalDate endDate, String username);
}
