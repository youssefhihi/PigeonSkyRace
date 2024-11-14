package ma.yc.PigeonSkyRace.result.application.dto.request;

import jakarta.validation.constraints.NotNull;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.BandNumber;

import java.time.LocalDateTime;

        public record ResultRequestDto(
                @NotNull BandNumber bandNumber,
                @NotNull LocalDateTime dateArrival
                ) {
        }
