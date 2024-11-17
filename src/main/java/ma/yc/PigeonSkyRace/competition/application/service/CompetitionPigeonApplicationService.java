package ma.yc.PigeonSkyRace.competition.application.service;

import ma.yc.PigeonSkyRace.competition.domain.ValueObject.CompetitionPigeonId;
import ma.yc.PigeonSkyRace.competition.domain.entity.Competition;
import ma.yc.PigeonSkyRace.competition.domain.entity.CompetitionPigeon;
import ma.yc.PigeonSkyRace.competition.domain.entity.SeasonPigeon;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CompetitionPigeonApplicationService {
    CompetitionPigeon findBySeasonPigeonAndCompetition(SeasonPigeon seasonPigeon, Competition competition);
    CompetitionPigeon findById(CompetitionPigeonId id);
    List<CompetitionPigeon> findByCompetition(Competition competition);

}
