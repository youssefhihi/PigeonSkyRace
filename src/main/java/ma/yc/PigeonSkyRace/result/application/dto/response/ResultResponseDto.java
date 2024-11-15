package ma.yc.PigeonSkyRace.result.application.dto.response;

import jakarta.validation.constraints.NotNull;
import ma.yc.PigeonSkyRace.competition.domain.entity.CompetitionPigeon;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.BandNumber;
import ma.yc.PigeonSkyRace.result.domain.valueObject.ResultId;

import java.time.LocalDateTime;

public record ResultResponseDto(
        ResultId id,
        Double distance,
        Double speed,
        Double points,
        BandNumber bandNumber,
        LocalDateTime createdDate,
        LocalDateTime dateArrival

) {
}
