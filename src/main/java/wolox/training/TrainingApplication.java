package wolox.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@EnableReactiveMongoRepositories
//@EnableWebFluxSecurity
public class TrainingApplication {

//	@Bean
//	CommandLineRunner users(UserRepository userRepository) {
//		return args -> {
//			userRepository
//				.deleteAll()
//				.subscribe(null, null, () -> {
//					Stream
//						.of(new User("car", "car", LocalDate.now()), new User("car2", "car2", LocalDate.now()))
//						.forEach(user -> {
//							userRepository
//								.save(user)
//								.subscribe(emp -> {
//									System.out.println(emp);
//								});
//						});
//				});
//		};
//	}

	public static void main(String[] args) {
		SpringApplication.run(TrainingApplication.class, args);
	}

}
