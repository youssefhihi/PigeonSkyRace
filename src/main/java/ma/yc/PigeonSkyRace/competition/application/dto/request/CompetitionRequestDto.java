package ma.yc.PigeonSkyRace.competition.application.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.AdmissionPercentage;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonId;
import ma.yc.PigeonSkyRace.piegon.application.dto.request.CoordinateRequestDTO;

import java.time.LocalDateTime;

public record CompetitionRequestDto(
        @NotBlank String name,
        @NotBlank String description,
        @Positive Integer maxPigeons,
        @Valid
        @NotNull(message = "coordinate cannot be null" )
        CoordinateRequestDTO coordinate,
        @Valid
        @NotNull AdmissionPercentage admissionPercentage,
        @NotNull LocalDateTime dateStart,
        @NotNull LocalDateTime dateEnd,
        @NotNull SeasonId seasonId
        ) {

}
