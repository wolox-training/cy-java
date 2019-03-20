package wolox.training.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import wolox.training.models.User;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {

    User findByUsername(String username);

    Mono<User> findOne(Long id);

//    @Query(
//        "SELECT u FROM User u WHERE ((u.birthdate > :startDate AND u.birthdate < :endDate) OR u.birthdate IS NULL) "
//            + "AND (u.username = :username OR u.username IS NULL)")
//    Page<User> findByBirthdateBetweenAndUsernameContainingIgnoreCase(LocalDate startDate,
//        LocalDate endDate, String username, Pageable pageable);
//
//    @Query(
//        "SELECT u FROM User u WHERE (u.username LIKE %:username% OR u.username IS NULL) AND (u.name LIKE %:name% OR u.name IS NULL) "
//            +
//            "AND (u.birthdate = :birthdate OR u.birthdate IS NULL)")
//    Page<User> getAll(@Param("username") String username, @Param("name") String name,
//        @Param("birthdate") LocalDate birthdate, Pageable pageable);

//    Mono<User> findOne(Long id);
}
