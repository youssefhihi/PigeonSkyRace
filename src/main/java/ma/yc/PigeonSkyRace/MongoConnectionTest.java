package ma.yc.PigeonSkyRace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MongoConnectionTest implements CommandLineRunner {
    @Autowired
    private TestRepository testRepository;

    @Override
    public void run(String... args) {
        TestEntity entity = new TestEntity();
        entity.setName("Startup Test");
        testRepository.save(entity);

        System.out.println("Database connection is working!");
    }
}