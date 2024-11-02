package ma.yc.PigeonSkyRace.competition.infrastructure.mapping;

import ma.yc.PigeonSkyRace.common.infrastructure.mapper.GenericMapper;
import ma.yc.PigeonSkyRace.competition.application.dto.request.SeasonRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.SeasonResponseDto;
import ma.yc.PigeonSkyRace.competition.domain.entity.Season;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeasonMapper extends GenericMapper<Season, SeasonRequestDto, SeasonResponseDto> {
}
