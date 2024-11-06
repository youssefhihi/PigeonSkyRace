package ma.yc.PigeonSkyRace.piegon.domain.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.PigeonSkyRace.piegon.application.dto.request.LoftRequestDTO;
import ma.yc.PigeonSkyRace.piegon.application.dto.response.LoftResponseDTO;
import ma.yc.PigeonSkyRace.piegon.application.mapper.LoftMapper;
import ma.yc.PigeonSkyRace.piegon.domain.model.entity.Loft;
import ma.yc.PigeonSkyRace.piegon.domain.service.LoftDomainService;
import ma.yc.PigeonSkyRace.piegon.infrastructure.repository.LoftRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultLoftDomainService implements LoftDomainService {

    private final LoftRepository repository;
    private final LoftMapper mapper;

    public LoftResponseDTO create ( LoftRequestDTO dto ) {
        Loft loft = mapper.toEntity(dto);
        String uniqueName;

        do {
            uniqueName = generateUniqueLoftName();
        } while (repository.existsByName(uniqueName));

        loft.setName(uniqueName);
        return mapper.toDto(repository.save(loft));
    }

    private String generateUniqueLoftName () {
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"));
        String seconds = LocalTime.now().format(DateTimeFormatter.ofPattern("ss"));
        String uniqueSuffix = UUID.randomUUID().toString().replace("-", "").substring(0, 5);
        return String.format("LOFT-%s-%s-%s", year, seconds, uniqueSuffix);
    }
}
