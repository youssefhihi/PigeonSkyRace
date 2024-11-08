package ma.yc.PigeonSkyRace.competition.domain.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.PigeonSkyRace.competition.application.dto.request.SeasonPigeonRequestDTO;
import ma.yc.PigeonSkyRace.competition.application.dto.response.SeasonPigeonResponseDTO;
import ma.yc.PigeonSkyRace.competition.application.mapping.SeasonPigeonMapper;
import ma.yc.PigeonSkyRace.competition.application.service.SeasonApplicationService;
import ma.yc.PigeonSkyRace.competition.domain.entity.Season;
import ma.yc.PigeonSkyRace.competition.domain.entity.SeasonPigeon;
import ma.yc.PigeonSkyRace.competition.domain.service.SeasonPigeonDomainService;
import ma.yc.PigeonSkyRace.competition.infrastructure.repository.SeasonPigeonRepository;
import ma.yc.PigeonSkyRace.piegon.application.service.PigeonApplicationService;
import ma.yc.PigeonSkyRace.piegon.domain.model.aggregate.Pigeon;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DefaultSeasonPigeonService implements SeasonPigeonDomainService {

    private final SeasonPigeonRepository repository;
    private final SeasonPigeonMapper mapper;
    private final PigeonApplicationService pigeonApplicationService;
    private final SeasonApplicationService seasonApplicationService;

    @Override
    public SeasonPigeonResponseDTO associatePigeonWithSeason ( SeasonPigeonRequestDTO seasonPigeonDTO ) {

        Season season = seasonApplicationService.findById(seasonPigeonDTO.seasonId());
        Pigeon pigeon = pigeonApplicationService.findPigeonById(seasonPigeonDTO.pigeonId());

        SeasonPigeon seasonPigeon = new SeasonPigeon();
        seasonPigeon.setSeason(season);
        seasonPigeon.setPigeon(pigeon);
        repository.save(seasonPigeon);

        season.getSeasonPigeons().add(seasonPigeon);
        pigeon.getSeasonPigeons().add(seasonPigeon);

        log.info("Association completed successfully.");

        return mapper.toDto(seasonPigeon);

    }
}
