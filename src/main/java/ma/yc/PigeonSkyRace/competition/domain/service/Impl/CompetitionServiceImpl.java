package ma.yc.PigeonSkyRace.competition.domain.service.Impl;

import ma.yc.PigeonSkyRace.competition.application.dto.request.CompetitionRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.CompetitionResponseDto;
import ma.yc.PigeonSkyRace.competition.domain.service.CompetitionService;
import ma.yc.PigeonSkyRace.competition.domain.repository.CompetitionRepository;
import ma.yc.PigeonSkyRace.competition.infrastructure.mapping.CompetitionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final CompetitionMapper competitionMapper;


    @Autowired
    public CompetitionServiceImpl(CompetitionRepository competitionRepository, CompetitionMapper competitionMapper) {
        this.competitionRepository = competitionRepository;
        this.competitionMapper = competitionMapper;
    }


    @Override
    public CompetitionResponseDto createCompetition(CompetitionRequestDto competitionRequestDto) {
        return competitionMapper.toDto(competitionRepository.save(competitionMapper.toEntity(competitionRequestDto)));
    }

    @Override
    public CompetitionResponseDto updateCompetition(CompetitionRequestDto competitionRequestDto) {
        return competitionMapper.toDto(competitionRepository.save(competitionMapper.toEntity(competitionRequestDto)));
    }

    @Override
    public Boolean deleteCompetition(UUID id) {
        if (competitionRepository.existsById(id)) {
            competitionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
