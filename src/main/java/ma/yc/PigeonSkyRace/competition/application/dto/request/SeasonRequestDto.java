package ma.yc.PigeonSkyRace.competition.application.dto.request;

import jakarta.validation.constraints.NotNull;

public record SeasonRequestDto(
        @NotNull String name,
        @NotNull String description,
        @NotNull Boolean isActive
) {
}
