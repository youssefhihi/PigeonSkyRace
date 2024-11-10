package ma.yc.PigeonSkyRace;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@OpenAPIDefinition(
		info = @Info(
				title = "Pigeon Sky Race API",
				version = "1.0",
				description = "API documentation for managing pigeon racing competitions with MongoDB"
		)
)
@SpringBootApplication(scanBasePackages = "ma.yc.PigeonSkyRace")
@EnableMongoRepositories
@EnableScheduling
public class PigeonSkyRaceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(PigeonSkyRaceApplication.class, args);

	

	}

}
