package ma.yc.PigeonSkyRace.result.application.mapping;

import ma.yc.PigeonSkyRace.result.application.dto.request.ResultRequestDto;
import ma.yc.PigeonSkyRace.result.application.dto.response.ResultResponseDto;
import ma.yc.PigeonSkyRace.result.domain.entity.Result;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResultMapper {
    Result toEntity(ResultRequestDto result);


    ResultResponseDto toDto(Result result);
}
