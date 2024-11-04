package ma.yc.PigeonSkyRace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "ma.yc.PigeonSkyRace")
@EnableMongoRepositories
public class PigeonSkyRaceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(PigeonSkyRaceApplication.class, args);

	

	}

}
