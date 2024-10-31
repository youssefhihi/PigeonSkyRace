package ma.yc.PigeonSkyRace.competition.domain.entity;

import lombok.Getter;
import lombok.Setter;
import ma.yc.PigeonSkyRace.piegon.domain.entity.Pigeon;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "pigeon_seasons")
public class PigeonSeason {
    @Id
    private String id;

    @DBRef
    private Pigeon pigeon;

    @DBRef
    private Season season;

    @CreatedDate
    private LocalDateTime createdDate;
}
