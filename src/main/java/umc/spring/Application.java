package umc.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.spring.service.store.StoreQueryService;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner run(ApplicationContext context) {
		return args -> {
			try {
				StoreQueryService storeService = context.getBean(StoreQueryService.class);

				String name = "요아정";
				Float score = 4.0f;

				System.out.println("Executing findStoresByNameAndScore with parameters:");
				System.out.println("Name: " + name);
				System.out.println("Score: " + score);

				storeService.findStoresByNameAndScore(name, score)
						.forEach(System.out::println);
			} catch (Exception e) {
				e.printStackTrace(); // 여기서 예외가 발생하면 출력
			}
		};
	}

}
