package ma.yc.PigeonSkyRace.competition.application.mapping;

import ma.yc.PigeonSkyRace.competition.application.dto.request.SeasonPigeonRequestDTO;
import ma.yc.PigeonSkyRace.competition.application.dto.response.SeasonPigeonResponseDTO;
import ma.yc.PigeonSkyRace.competition.domain.entity.SeasonPigeon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SeasonPigeonMapper {

    SeasonPigeon toEntity ( SeasonPigeonRequestDTO dto );

    @Mapping(target = "pigeonId", expression = "java(entity.getPigeon().getId().toHexString())")
    @Mapping(target = "seasonId", expression = "java(entity.getSeason().getId().toHexString())")
    SeasonPigeonResponseDTO toDto ( SeasonPigeon entity );
}