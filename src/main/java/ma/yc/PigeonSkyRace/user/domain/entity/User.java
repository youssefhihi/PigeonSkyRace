package ma.yc.PigeonSkyRace.user.domain.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import ma.yc.PigeonSkyRace.piegon.domain.entity.Loft;
import ma.yc.PigeonSkyRace.user.domain.enums.Role;
import ma.yc.PigeonSkyRace.user.domain.valueObject.Email;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Document(collection = "users")
public class User {

    @Id
    private UUID id;
    @NotBlank
    private String name;
    @NotBlank
    private String userName;

    private Email email;

    @NotBlank
    private String password;

    private Role role;

    @DBRef
    private List<Loft> lofts;

    @CreatedDate
    private LocalDateTime createdDate;
}
