package ma.yc.PigeonSkyRace.piegon.application.dto.request;

import jakarta.validation.constraints.NotNull;
import ma.yc.PigeonSkyRace.common.application.validation.EntityExists;
import ma.yc.PigeonSkyRace.user.domain.model.aggregate.User;

public record LoftRequestDTO(
        @NotNull CoordinateRequestDTO coordinate,

        @EntityExists(entity = User.class, message = "The specified breeder does not exist")
        @NotNull String userId) {
}