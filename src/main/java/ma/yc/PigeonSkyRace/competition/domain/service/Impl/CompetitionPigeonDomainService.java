package ma.yc.PigeonSkyRace.competition.domain.service.Impl;

import lombok.RequiredArgsConstructor;
import ma.yc.PigeonSkyRace.competition.application.dto.request.CompetitionPigeonRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.CompetitionPigeonResponseDto;
import ma.yc.PigeonSkyRace.competition.application.service.CompetitionPigeonApplicationService;
import ma.yc.PigeonSkyRace.competition.domain.entity.Competition;
import ma.yc.PigeonSkyRace.competition.domain.entity.CompetitionPigeon;
import ma.yc.PigeonSkyRace.competition.domain.entity.SeasonPigeon;
import ma.yc.PigeonSkyRace.competition.infrastructure.repository.CompetitionPigeonRepository;
import ma.yc.PigeonSkyRace.competition.domain.service.CompetitionPigeonService;
import ma.yc.PigeonSkyRace.competition.infrastructure.mapping.CompetitionPigeonMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompetitionPigeonDomainService implements CompetitionPigeonService, CompetitionPigeonApplicationService {

    private final CompetitionPigeonRepository repository;
    private final CompetitionPigeonMapper mapper;

    @Override
    public CompetitionPigeonResponseDto registerToCompetition(SeasonPigeon seasonPigeon, Competition competition) {

        if (!competition.getSeasonId().equals(seasonPigeon.getSeason().getId())) {
            throw new IllegalArgumentException("This pigeon is not registered in the same season as the competition.");
        }

        CompetitionPigeonRequestDto requestDto = new CompetitionPigeonRequestDto(seasonPigeon, competition);

        repository.findBySeasonPigeonAndCompetition(requestDto.seasonPigeon(), requestDto.competition()).ifPresent(p -> {
            throw new IllegalArgumentException("This pigeon is already registered in the competition.");
        });

        CompetitionPigeon savedCompetitionPigeon = repository.save(mapper.toEntity(requestDto));
        return mapper.toDto(savedCompetitionPigeon);
    }
}
