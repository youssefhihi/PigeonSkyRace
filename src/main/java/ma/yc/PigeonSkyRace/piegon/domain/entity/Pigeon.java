package ma.yc.PigeonSkyRace.piegon.domain.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import ma.yc.PigeonSkyRace.piegon.domain.valueObject.Gender;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Document(collection = "pigeons")
public class Pigeon {

    @Id
    private UUID id;

    @NotBlank
    private String bandNumber;

    @NotNull
    private Gender gender;

    @Positive
    private double age;

    @NotBlank
    private String color;

    @DBRef
    private Loft loft;

    @CreatedDate
    private LocalDateTime createdDate;
}