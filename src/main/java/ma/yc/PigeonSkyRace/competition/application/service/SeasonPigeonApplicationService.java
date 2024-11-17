package ma.yc.PigeonSkyRace.competition.application.service;

import ma.yc.PigeonSkyRace.competition.domain.entity.Season;
import ma.yc.PigeonSkyRace.competition.domain.entity.SeasonPigeon;
import ma.yc.PigeonSkyRace.piegon.domain.model.aggregate.Pigeon;

public interface SeasonPigeonApplicationService {
    SeasonPigeon findSeasonPigeonPigeonAndSeason(Season season, Pigeon pigeon);
}
