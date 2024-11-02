package ma.yc.PigeonSkyRace.piegon.domain.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.Coordinate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Document(collection = "lofts")
public class Loft {
    @Id
    private UUID id;

    @NotBlank
    private String name;

    private Coordinate coordinate;

    @CreatedDate
    private LocalDateTime createdDate;
}
