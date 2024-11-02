package ma.yc.PigeonSkyRace.competition.application.dto.response;


import ma.yc.PigeonSkyRace.competition.domain.entity.Competition;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record SeasonResponseDto(
        UUID id,
        String name,
        String description,
        Boolean isActive,
        LocalDateTime createdAt,
        List<Competition>competitions
) {
}
