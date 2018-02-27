package autentia.apiRestTnt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ApiRestTntApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ApiRestTntApplication.class, args);
	}
}
