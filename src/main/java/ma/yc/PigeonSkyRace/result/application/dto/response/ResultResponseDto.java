package ma.yc.PigeonSkyRace.result.application.dto.response;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ResultResponseDto(
        @NotNull LocalDateTime dateArrival
) {
}
