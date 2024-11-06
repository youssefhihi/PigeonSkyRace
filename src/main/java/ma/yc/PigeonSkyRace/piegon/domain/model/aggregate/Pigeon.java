package ma.yc.PigeonSkyRace.piegon.domain.model.aggregate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import ma.yc.PigeonSkyRace.piegon.domain.model.entity.Loft;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.Gender;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.LoftId;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.PigeonId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "pigeons")
public class Pigeon {

    @Id
    private PigeonId id;

    @NotBlank
    private String bandNumber;

    @NotNull
    private Gender gender;

    @Positive
    private double age;

    @NotBlank
    private String color;

    private LoftId loft;

    @CreatedDate
    private LocalDateTime createdDate;

    public Pigeon () {
        this.id = new PigeonId();
    }
}