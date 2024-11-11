package ma.yc.PigeonSkyRace.competition.application.service;

import ma.yc.PigeonSkyRace.competition.domain.entity.Competition;
import ma.yc.PigeonSkyRace.competition.domain.entity.CompetitionPigeon;
import ma.yc.PigeonSkyRace.competition.domain.entity.SeasonPigeon;

import java.util.Optional;

public interface CompetitionPigeonApplicationService {
    CompetitionPigeon findBySeasonPigeonAndCompetition(SeasonPigeon seasonPigeon, Competition competition);

}
