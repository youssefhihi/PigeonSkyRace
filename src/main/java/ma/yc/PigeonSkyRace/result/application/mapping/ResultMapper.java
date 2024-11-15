package ma.yc.PigeonSkyRace.result.application.mapping;

import ma.yc.PigeonSkyRace.competition.domain.entity.CompetitionPigeon;
import ma.yc.PigeonSkyRace.result.application.dto.request.ResultRequestDto;
import ma.yc.PigeonSkyRace.result.application.dto.response.ResultResponseDto;
import ma.yc.PigeonSkyRace.result.domain.entity.Result;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResultMapper {
    Result toEntity(ResultRequestDto result);


    @Mapping(target = "bandNumber" , expression = ("java(result.getCompetitionPigeon().getSeasonPigeon().getPigeon().getBandNumber())"))
    ResultResponseDto toDto(Result result);
}
