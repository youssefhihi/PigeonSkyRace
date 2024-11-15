package ma.yc.PigeonSkyRace.piegon.domain.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.PigeonSkyRace.common.domain.exception.NotFoundException;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.Coordinate;
import ma.yc.PigeonSkyRace.piegon.application.dto.request.LoftRequestDTO;
import ma.yc.PigeonSkyRace.piegon.application.dto.response.LoftResponseDTO;
import ma.yc.PigeonSkyRace.piegon.application.mapper.LoftMapper;
import ma.yc.PigeonSkyRace.piegon.application.service.LoftApplicationService;
import ma.yc.PigeonSkyRace.piegon.domain.model.entity.Loft;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.LoftId;
import ma.yc.PigeonSkyRace.piegon.domain.service.LoftDomainService;
import ma.yc.PigeonSkyRace.piegon.domain.service.LoftNameGenerator;
import ma.yc.PigeonSkyRace.piegon.infrastructure.repository.LoftRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Validated
public class DefaultLoftDomainService implements LoftDomainService, LoftApplicationService {

    private final LoftRepository repository;
    private final LoftMapper mapper;
    private final LoftNameGenerator loftNameGenerator;

    @Override
    public boolean existsById ( LoftId id ) {
        return repository.existsById(id);
    }

    @Override
    public LoftResponseDTO findById ( LoftId id ) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(() -> new NotFoundException("Loft", id));
    }

    @Override
    public List<LoftResponseDTO> findAll () {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public LoftResponseDTO create ( LoftRequestDTO dto ) {

        Loft loft = mapper.toEntity(dto);
        String uniqueName = generateUniqueLoftName();
        loft.setName(uniqueName);

        return mapper.toDto(repository.save(loft));
    }

    @Override
    public Coordinate geLoftCoordinate ( LoftId loftId ) {

        Loft loft = repository.findById(loftId).orElseThrow(() -> new NotFoundException("Loft", loftId));

        return loft.getCoordinate();
    }

    private String generateUniqueLoftName () {
        String uniqueName;
        do {
            uniqueName = loftNameGenerator.generateUniqueLoftName();
        } while (repository.existsByName(uniqueName));
        return uniqueName;
    }
}
