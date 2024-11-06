package ma.yc.PigeonSkyRace.piegon.application.dto.response;

import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.Gender;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.LoftId;

import java.time.LocalDateTime;

public record PigeonResponseDTO(String id, String bandNumber, Gender gender, double age, String color, String loftId,
                                LocalDateTime createdDate) {

}
