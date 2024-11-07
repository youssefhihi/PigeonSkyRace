package ma.yc.PigeonSkyRace.competition.infrastructure.mapping;

import ma.yc.PigeonSkyRace.competition.application.dto.request.CompetitionPigeonRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.CompetitionPigeonResponseDto;
import ma.yc.PigeonSkyRace.competition.domain.entity.CompetitionPigeon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompetitionPigeonMapper {
    CompetitionPigeon toEntity(CompetitionPigeonRequestDto seasonPigeonRequestDto);

    CompetitionPigeonResponseDto toDto(CompetitionPigeon seasonPigeon);
}