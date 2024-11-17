package ma.yc.PigeonSkyRace.piegon.domain.service;

import ma.yc.PigeonSkyRace.piegon.application.dto.request.LoftRequestDTO;
import ma.yc.PigeonSkyRace.piegon.application.dto.response.LoftResponseDTO;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.LoftId;

import java.util.List;

public interface LoftDomainService {
    LoftResponseDTO create ( LoftRequestDTO dto );
    boolean existsById( LoftId id);
    LoftResponseDTO findById(LoftId id);
    List<LoftResponseDTO> findAll();
}
