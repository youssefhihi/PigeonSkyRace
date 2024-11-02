package ma.yc.PigeonSkyRace.competition.domain.service.Impl;

import ma.yc.PigeonSkyRace.competition.application.dto.request.SeasonRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.SeasonResponseDto;
import ma.yc.PigeonSkyRace.competition.domain.service.interfaces.SeasonService;
import ma.yc.PigeonSkyRace.competition.domain.entity.Season;
import ma.yc.PigeonSkyRace.competition.domain.repository.SeasonRepository;
import ma.yc.PigeonSkyRace.competition.infrastructure.mapping.SeasonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SeasonServiceImpl implements SeasonService {

    private final SeasonRepository seasonRepository;
    private final SeasonMapper seasonMapper;

    @Autowired
    public SeasonServiceImpl(SeasonRepository seasonRepository, SeasonMapper seasonMapper) {
        this.seasonRepository = seasonRepository;
        this.seasonMapper = seasonMapper;
    }


    @Override
    public SeasonResponseDto createSeason(SeasonRequestDto seasonRequestDto) {
        Season season = seasonMapper.toEntity(seasonRequestDto);
        season.setId(UUID.randomUUID());
        return seasonMapper.toDto(seasonRepository.save(season));
    }

    @Override
    public SeasonResponseDto updateSeason(SeasonRequestDto seasonRequestDto) {
        return seasonMapper.toDto(seasonRepository.save(seasonMapper.toEntity(seasonRequestDto)));
    }

    @Override
    public Boolean deleteSeason(UUID id) {
        if (seasonRepository.existsById(id)) {
            seasonRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
