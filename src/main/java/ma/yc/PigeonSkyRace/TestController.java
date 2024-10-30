package ma.yc.PigeonSkyRace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private TestRepository testRepository;

    @GetMapping("/test-db-connection")
    public ResponseEntity<String> testDbConnection() {
        TestEntity entity = new TestEntity();
        entity.setName("Connection Test");
        testRepository.save(entity);

        return ResponseEntity.ok("Database connection is successful!");
    }

    @GetMapping("/hi")
    public ResponseEntity<String> hi(){
        return ResponseEntity.ok("hello!");
    }
}