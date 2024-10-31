package ma.yc.PigeonSkyRace.competition.application.dto.request;

import jakarta.validation.constraints.NotNull;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.AdmissionPercentage;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.Coordinate;

import java.time.LocalDateTime;

public record CompetitionRequestDto(
       @NotNull String name,
       @NotNull String description,
       @NotNull Coordinate coordinate,
       @NotNull Integer maxPigeons,
       @NotNull AdmissionPercentage admissionPercentage,
       @NotNull LocalDateTime dateStart,
       @NotNull LocalDateTime dateEnd
) {
}
