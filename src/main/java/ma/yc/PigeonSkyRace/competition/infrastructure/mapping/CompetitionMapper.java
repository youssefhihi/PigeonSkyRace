package ma.yc.PigeonSkyRace.competition.infrastructure.mapping;

import ma.yc.PigeonSkyRace.common.infrastructure.mapper.GenericMapper;
import ma.yc.PigeonSkyRace.competition.application.dto.request.CompetitionRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.CompetitionResponseDto;
import ma.yc.PigeonSkyRace.competition.domain.entity.Competition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompetitionMapper extends GenericMapper<Competition, CompetitionRequestDto, CompetitionResponseDto> {
}
