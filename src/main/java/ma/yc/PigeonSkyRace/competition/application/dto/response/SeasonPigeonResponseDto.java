package ma.yc.PigeonSkyRace.competition.application.dto.response;

import ma.yc.PigeonSkyRace.competition.domain.entity.Season;
import ma.yc.PigeonSkyRace.piegon.domain.model.aggregate.Pigeon;

public record SeasonPigeonResponseDto(Pigeon pigeon, Season  season) {
}
