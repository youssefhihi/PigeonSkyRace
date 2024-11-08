package ma.yc.PigeonSkyRace.competition.domain.service.Impl;

import ma.yc.PigeonSkyRace.common.domain.exception.NotFoundException;
import ma.yc.PigeonSkyRace.competition.application.dto.request.SeasonPigeonRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.SeasonPigeonResponseDto;
import ma.yc.PigeonSkyRace.competition.application.service.SeasonPigeonApplicationService;
import ma.yc.PigeonSkyRace.competition.domain.Exception.FailedToRegister;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonPigeonId;
import ma.yc.PigeonSkyRace.competition.domain.entity.SeasonPigeon;
import ma.yc.PigeonSkyRace.competition.infrastructure.repository.SeasonPigeonRepository;
import ma.yc.PigeonSkyRace.competition.domain.service.SeasonPigeonService;
import ma.yc.PigeonSkyRace.competition.infrastructure.mapping.SeasonPigeonMapper;
import org.springframework.stereotype.Service;

@Service
public class SeasonPigeonDomainService implements SeasonPigeonService, SeasonPigeonApplicationService {
    private final SeasonPigeonRepository repository;
    private final SeasonPigeonMapper mapper;

    public SeasonPigeonDomainService(SeasonPigeonRepository repository, SeasonPigeonMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public SeasonPigeonResponseDto RegisterToSeason(SeasonPigeonRequestDto seasonPigeonRequestDto) {
        repository.findByPigeonAndSeason(
                seasonPigeonRequestDto.pigeon(), seasonPigeonRequestDto.season()
        ).orElseThrow(() -> new FailedToRegister("This pigeon is already registered in the season."));

        SeasonPigeon savedSeasonPigeon = repository.save(mapper.toEntity(seasonPigeonRequestDto));

        return mapper.toDto(savedSeasonPigeon);
    }

    @Override
    public SeasonPigeonResponseDto getSeasonById(SeasonPigeonId seasonPigeonId){
        SeasonPigeon seasonPigeon = repository.findById(seasonPigeonId).orElseThrow(() ->  new NotFoundException("seasonPigeon", seasonPigeonId));
        return mapper.toDto(seasonPigeon);
    }



}
