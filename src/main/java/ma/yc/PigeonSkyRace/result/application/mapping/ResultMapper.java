package ma.yc.PigeonSkyRace.result.application.mapping;

import ma.yc.PigeonSkyRace.piegon.domain.model.entity.Loft;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.LoftId;
import ma.yc.PigeonSkyRace.piegon.infrastructure.repository.LoftRepository;
import ma.yc.PigeonSkyRace.result.application.dto.request.ResultRequestDto;
import ma.yc.PigeonSkyRace.result.application.dto.response.ResultResponseDto;
import ma.yc.PigeonSkyRace.result.domain.entity.Result;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ResultMapper {
    @Autowired
    private LoftRepository loftRepository;

    public abstract Result toEntity(ResultRequestDto result);

    @Mapping(target = "bandNumber", expression = "java(result.getCompetitionPigeon().getSeasonPigeon().getPigeon().getBandNumber())")
    @Mapping(target = "loft", expression = "java(getLoftName(result.getCompetitionPigeon().getSeasonPigeon().getPigeon().getLoft()))")
    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
    public abstract ResultResponseDto toDto(Result result);

    protected String getLoftName(LoftId loftId) {
        return loftRepository.findById(loftId)
                .map(Loft::getName)
                .orElse(null);
    }
}
