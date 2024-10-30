package ma.yc.PigeonSkyRace;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "testCollection")
@Data
public class TestEntity {
    @Id
    private String id;
    private String name;
}
