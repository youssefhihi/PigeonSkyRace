package ma.yc.PigeonSkyRace.competition.domain.service.interfaces;

import ma.yc.PigeonSkyRace.competition.application.dto.request.SeasonRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.SeasonResponseDto;

import java.util.UUID;

public interface SeasonService {

    SeasonResponseDto createSeason(SeasonRequestDto seasonRequestDto);

    SeasonResponseDto updateSeason(SeasonRequestDto seasonRequestDto);

    Boolean deleteSeason(UUID seasonId);
}
