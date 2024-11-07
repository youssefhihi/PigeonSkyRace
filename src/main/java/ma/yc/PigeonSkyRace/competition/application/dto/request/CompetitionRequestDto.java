package ma.yc.PigeonSkyRace.competition.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.AdmissionPercentage;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonId;

import java.time.LocalDateTime;

public record CompetitionRequestDto(
        @NotBlank String name,
        @NotBlank String description,
        @NotNull Integer maxPigeons,
        @NotNull AdmissionPercentage admissionPercentage,
        @NotNull LocalDateTime dateStart,
        @NotNull LocalDateTime dateEnd,
        @NotNull SeasonId seasonId
        ) {

}
