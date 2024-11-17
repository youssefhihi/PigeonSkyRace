package ma.yc.PigeonSkyRace.competition.application.dto.response;

import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonPigeonId;
import ma.yc.PigeonSkyRace.competition.domain.entity.Season;
import ma.yc.PigeonSkyRace.piegon.domain.model.aggregate.Pigeon;

public record SeasonPigeonResponseDto(
        SeasonPigeonId id,
        Pigeon pigeon,
        Season  season
) {
}
