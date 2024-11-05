package ma.yc.PigeonSkyRace.competition.application.dto.response;

import ma.yc.PigeonSkyRace.competition.domain.ValueObject.AdmissionPercentage;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.CompetitionId;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.Coordinate;

import java.time.LocalDateTime;
import java.util.UUID;

public record CompetitionResponseDto(
        CompetitionId id,
        String name,
        String description,
        Coordinate coordinate,
        Integer maxPigeons,
        AdmissionPercentage admissionPercentage,
        LocalDateTime dateStart,
        LocalDateTime dateEnd,
        LocalDateTime createdAt
){
}
