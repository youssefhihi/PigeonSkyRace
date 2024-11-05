package ma.yc.PigeonSkyRace.competition.domain.service.Impl;

import ma.yc.PigeonSkyRace.common.domain.exception.NotFoundException;
import ma.yc.PigeonSkyRace.competition.application.dto.request.CompetitionRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.CompetitionResponseDto;
import ma.yc.PigeonSkyRace.competition.application.events.CompetitionCreatedEvent;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.CompetitionId;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.Coordinate;
import ma.yc.PigeonSkyRace.competition.domain.entity.Competition;
import ma.yc.PigeonSkyRace.competition.domain.entity.Season;
import ma.yc.PigeonSkyRace.competition.domain.service.CompetitionService;
import ma.yc.PigeonSkyRace.competition.domain.repository.CompetitionRepository;
import ma.yc.PigeonSkyRace.competition.infrastructure.mapping.CompetitionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository repository;
    private final CompetitionMapper mapper;
    private final ApplicationEventPublisher eventPublisher;



    @Autowired
    public CompetitionServiceImpl(CompetitionRepository repository, CompetitionMapper mapper, ApplicationEventPublisher eventPublisher) {
        this.repository = repository;
        this.mapper = mapper;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public CompetitionResponseDto createCompetition(CompetitionRequestDto competitionRequestDto) {
        Competition competition = mapper.toEntity(competitionRequestDto);
        Competition savedCompetition = repository.save(competition);
        eventPublisher.publishEvent(new CompetitionCreatedEvent(savedCompetition, competitionRequestDto.seasonId()));
        return mapper.toDto(savedCompetition);
    }

    @Override
    public CompetitionResponseDto getCompetition(CompetitionId id){
        Competition competition = repository.findById(id).orElseThrow(() -> new NotFoundException("Competition", id));
        return mapper.toDto(competition);
    }

    @Override
    public List<CompetitionResponseDto> getAllCompetitions() {
        List<Competition> competitions = repository.findAll();
        return   competitions.stream().map(mapper::toDto).collect(Collectors.toList());

    }


    @Override
    public Boolean updateCompetition(CompetitionId id, Coordinate coordinate) {
        if(repository.existsById(id)){
            repository.updateCoordinateById(id, coordinate);
            return true;
        }
       throw new NotFoundException("Competition", id);
    }

}
