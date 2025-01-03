package ma.yc.PigeonSkyRace.result.domain.entity;

import com.mongodb.lang.Nullable;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import ma.yc.PigeonSkyRace.competition.domain.entity.CompetitionPigeon;
import ma.yc.PigeonSkyRace.result.domain.valueObject.ResultId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Document(collection = "results")
public class Result {
    @Id
    private ResultId id;

    @DateTimeFormat
    private LocalDateTime dateArrival;

    @Positive
    private Double distance;

    @Positive
    private Double speed;

    @Positive
    @Size(min = 1, max = 100)
    private Double points;

    @DBRef
    CompetitionPigeon competitionPigeon;

    @CreatedDate
    private LocalDateTime createdDate;

    public Result() {
        this.id = new ResultId();
    }
}
