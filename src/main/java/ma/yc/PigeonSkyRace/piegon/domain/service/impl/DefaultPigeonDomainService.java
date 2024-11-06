package ma.yc.PigeonSkyRace.piegon.domain.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.PigeonSkyRace.piegon.application.dto.request.PigeonRequestDTO;
import ma.yc.PigeonSkyRace.piegon.application.dto.response.PigeonResponseDTO;
import ma.yc.PigeonSkyRace.piegon.application.mapper.PigeonMapper;
import ma.yc.PigeonSkyRace.piegon.domain.service.PigeonDomainService;
import ma.yc.PigeonSkyRace.piegon.infrastructure.repository.PigeonRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultPigeonDomainService implements PigeonDomainService {
    private final PigeonRepository repository;
    private final PigeonMapper mapper;

    @Override
    public PigeonResponseDTO create ( PigeonRequestDTO dto ) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

}
