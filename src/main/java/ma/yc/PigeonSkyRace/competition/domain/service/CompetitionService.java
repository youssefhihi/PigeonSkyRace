package ma.yc.PigeonSkyRace.competition.domain.service;

import ma.yc.PigeonSkyRace.competition.application.dto.request.CompetitionRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.CompetitionResponseDto;

import java.util.UUID;

public interface CompetitionService {

    CompetitionResponseDto createCompetition(CompetitionRequestDto seasonRequestDto);

    CompetitionResponseDto updateCompetition(CompetitionRequestDto seasonRequestDto);

    Boolean deleteCompetition(UUID seasonId);
}
