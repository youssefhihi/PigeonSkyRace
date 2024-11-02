package ma.yc.PigeonSkyRace.media.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Getter
@Setter
@Document(collection = "media")
public class Media {
    @Id
    private UUID id;

    @Field
    private String url;

    @Field
    private String imageAble;

    @Field
    private String imageType;
}
