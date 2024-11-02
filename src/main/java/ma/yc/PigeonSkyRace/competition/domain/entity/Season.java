package ma.yc.PigeonSkyRace.competition.domain.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Document(collection = "seasons")
public class Season {
    @Id
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;


    @Field(name = "is_active")
    private Boolean isActive;


    @DBRef
    private List<Competition> competitions;

    @CreatedDate
    private LocalDateTime createdDate;

}
