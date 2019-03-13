package wolox.training.repositories;

import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wolox.training.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query(
        "SELECT u FROM User u WHERE ((u.birthdate > :startDate AND u.birthdate < :endDate) OR u.birthdate IS NULL) "
            + "AND (u.username = :username OR u.username IS NULL)")
    Page<User> findByBirthdateBetweenAndUsernameContainingIgnoreCase(LocalDate startDate,
        LocalDate endDate, String username, Pageable pageable);

    @Query(
        "SELECT u FROM User u WHERE (u.username LIKE %:username% OR u.username IS NULL) AND (u.name LIKE %:name% OR u.name IS NULL) "
            +
            "AND (u.birthdate = :birthdate OR u.birthdate IS NULL)")
    Page<User> getAll(String username, String name, LocalDate birthdate, Pageable pageable);
}
