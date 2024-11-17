package ma.yc.PigeonSkyRace.piegon.application.mapper;

import ma.yc.PigeonSkyRace.piegon.application.dto.request.PigeonRequestDTO;
import ma.yc.PigeonSkyRace.piegon.application.dto.response.PigeonResponseDTO;
import ma.yc.PigeonSkyRace.piegon.domain.model.aggregate.Pigeon;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.LoftId;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface PigeonMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "loft", source = "loftId", qualifiedByName = "stringToLoftId")
    Pigeon toEntity ( PigeonRequestDTO dto );

    @Mapping(target = "id", expression = "java(pigeon.getId().toHexString())")
    @Mapping(target = "loftId", expression = "java(pigeon.getLoft().toHexString())")
    @Mapping(target = "createdDate", source = "createdDate",dateFormat = "yyyy-MM-dd HH:mm:ss")
    PigeonResponseDTO toDto ( Pigeon pigeon );

    @Named("stringToLoftId")
    default LoftId stringToLoftId ( String loftId ) {
        if (loftId == null) {
            return null;
        }
        return new LoftId(new ObjectId(loftId));
    }
}
