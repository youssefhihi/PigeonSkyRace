package ma.yc.PigeonSkyRace.piegon.application.dto.request;

import jakarta.validation.constraints.NotNull;

public record CoordinateRequestDTO(

        @NotNull(message = "latitude cannot be null" ) Double latitude,
        @NotNull(message = "longitude cannot be null" ) Double longitude) {
}