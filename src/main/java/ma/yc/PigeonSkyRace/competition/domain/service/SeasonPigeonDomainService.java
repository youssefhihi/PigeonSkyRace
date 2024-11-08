package ma.yc.PigeonSkyRace.competition.domain.service;


import ma.yc.PigeonSkyRace.competition.application.dto.request.SeasonPigeonRequestDTO;
import ma.yc.PigeonSkyRace.competition.application.dto.response.SeasonPigeonResponseDTO;

public interface SeasonPigeonDomainService {
    SeasonPigeonResponseDTO associatePigeonWithSeason ( SeasonPigeonRequestDTO seasonPigeonDTO );
}
