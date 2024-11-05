package ma.yc.PigeonSkyRace.piegon.domain.model.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.Coordinate;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.LoftId;
import ma.yc.PigeonSkyRace.user.domain.model.aggregate.User;
import ma.yc.PigeonSkyRace.user.domain.model.valueobject.UserId;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "lofts")
public class Loft {
    @Id
    private LoftId id;

    @NotBlank
    private String name;

    private Coordinate coordinate;

    private UserId userId;


    @CreatedDate
    private LocalDateTime createdDate;

    public Loft () {
        this.id = new LoftId();
    }
}
