package ma.yc.PigeonSkyRace.piegon.domain.service;

import ma.yc.PigeonSkyRace.piegon.application.dto.request.PigeonRequestDTO;
import ma.yc.PigeonSkyRace.piegon.application.dto.response.PigeonResponseDTO;
import ma.yc.PigeonSkyRace.piegon.domain.model.aggregate.Pigeon;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.PigeonId;

import java.util.List;

public interface PigeonDomainService {
    PigeonResponseDTO create ( PigeonRequestDTO dto );
    List<PigeonResponseDTO> findAll();
    PigeonResponseDTO getSeasonById ( PigeonId value );
    Boolean deletePigeonById ( PigeonId value );


}
