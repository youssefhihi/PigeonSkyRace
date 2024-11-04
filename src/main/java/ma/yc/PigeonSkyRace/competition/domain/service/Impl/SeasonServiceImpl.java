package ma.yc.PigeonSkyRace.competition.domain.service.Impl;

import ma.yc.PigeonSkyRace.common.domain.exception.NotFoundException;
import ma.yc.PigeonSkyRace.competition.application.dto.request.SeasonRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.SeasonResponseDto;
import ma.yc.PigeonSkyRace.competition.domain.service.SeasonService;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonId;
import ma.yc.PigeonSkyRace.competition.domain.entity.Season;
import ma.yc.PigeonSkyRace.competition.domain.repository.SeasonRepository;
import ma.yc.PigeonSkyRace.competition.infrastructure.mapping.SeasonMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeasonServiceImpl implements SeasonService {

    private final SeasonRepository seasonRepository;
    private final SeasonMapper seasonMapper;

    public SeasonServiceImpl(SeasonRepository seasonRepository, SeasonMapper seasonMapper) {
        this.seasonRepository = seasonRepository;
        this.seasonMapper = seasonMapper;
    }

    @Override
    public List<SeasonResponseDto> getAllSeasons() {
        List<Season> seasons = seasonRepository.findAll();
        return   seasons.stream().map(seasonMapper::toDto).collect(Collectors.toList());

    }

    @Override
    public SeasonResponseDto getSeasonById(SeasonId seasonId) {
        Season season = seasonRepository.findById(seasonId).orElseThrow(() -> new NotFoundException("Season", seasonId));
        return seasonMapper.toDto(season);
    }

    @Override
    public SeasonResponseDto createSeason(SeasonRequestDto seasonRequestDto) {
        Season season = seasonMapper.toEntity(seasonRequestDto);
        return seasonMapper.toDto(seasonRepository.save(season));
    }

    @Override
    public SeasonResponseDto updateSeason(SeasonId seasonId,SeasonRequestDto seasonRequestDto) {
        Season season = seasonRepository.findById(seasonId).orElseThrow(() -> new NotFoundException("Season", seasonId));
        season.setDescription(seasonRequestDto.description());
        season.setIsActive(seasonRequestDto.isActive());
        season.setName(seasonRequestDto.name());
        return seasonMapper.toDto(seasonRepository.save(season));

    }

    @Override
    public Boolean deleteSeason(SeasonId id) {
        if (seasonRepository.existsById(id)) {
            seasonRepository.deleteById(id);
            return true;
        }
        throw new NotFoundException("Season",id);
    }

}