package ma.yc.PigeonSkyRace.competition.application.mapping;

import ma.yc.PigeonSkyRace.competition.application.dto.request.CompetitionPigeonRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.CompetitionPigeonResponseDto;
import ma.yc.PigeonSkyRace.competition.domain.entity.CompetitionPigeon;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CompetitionPigeonMapper {
    CompetitionPigeon toEntity(CompetitionPigeonRequestDto seasonPigeonRequestDto);

    CompetitionPigeonResponseDto toDto(CompetitionPigeon seasonPigeon);
}