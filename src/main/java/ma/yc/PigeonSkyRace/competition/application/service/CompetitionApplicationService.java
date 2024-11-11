package ma.yc.PigeonSkyRace.competition.application.service;

import ma.yc.PigeonSkyRace.competition.domain.entity.Competition;

import java.time.LocalDateTime;

public interface CompetitionApplicationService {
    Competition getCurrentCompetition();
}
