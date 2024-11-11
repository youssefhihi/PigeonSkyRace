package ma.yc.PigeonSkyRace.competition.application.service;

import ma.yc.PigeonSkyRace.competition.application.dto.response.CompetitionResponseDto;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.CompetitionId;
import ma.yc.PigeonSkyRace.competition.domain.entity.Competition;

import java.time.LocalDateTime;

public interface CompetitionApplicationService {
    CompetitionResponseDto getCompetition(CompetitionId id);

}
