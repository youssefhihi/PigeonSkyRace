package ma.yc.PigeonSkyRace.competition.domain.entity;


import ma.yc.PigeonSkyRace.piegon.domain.model.aggregate.Pigeon;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "competition_pigeons")
public class CompetitionPigeon {
    @Id
    private String id;

    @DBRef
    private Pigeon pigeon;

    @DBRef
    private Competition competition;

    @CreatedDate
    private LocalDateTime createdDate;
}
