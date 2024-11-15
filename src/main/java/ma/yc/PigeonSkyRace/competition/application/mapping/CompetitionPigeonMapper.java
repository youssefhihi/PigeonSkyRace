package ma.yc.PigeonSkyRace.competition.application.mapping;

import ma.yc.PigeonSkyRace.competition.application.dto.request.CompetitionPigeonRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.CompetitionPigeonResponseDto;
import ma.yc.PigeonSkyRace.competition.domain.entity.CompetitionPigeon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CompetitionPigeonMapper {
    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
    CompetitionPigeon toEntity(CompetitionPigeonRequestDto competitionPigeonRequestDto);

    CompetitionPigeonResponseDto toDto(CompetitionPigeon competitionPigeon);
}
