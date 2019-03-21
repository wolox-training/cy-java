package wolox.training.repositories;

import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wolox.training.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query(
        "SELECT u FROM User u WHERE (u.username LIKE %:username% OR u.username IS NULL) OR (u.name LIKE %:name% OR u.name IS NULL) "
            +
            "OR (u.birthdate = :birthdate OR u.birthdate IS NULL)")
    Page<User> getAll(@Param("username") String username, @Param("name") String name,
        @Param("birthdate") LocalDate birthdate, Pageable pageable);

    @Query(
        "SELECT u FROM User u WHERE ((u.birthdate > :startDate AND u.birthdate < :endDate) OR u.birthdate IS NULL) "
            + "AND (u.username = :username OR u.username IS NULL)")
    Page<User> findByBirthdateBetweenAndUsernameContainingIgnoreCase(
        @Param("startDate") LocalDate startDate,
        @Param("endDate") LocalDate endDate, @Param("username") String username, Pageable pageable);
}
