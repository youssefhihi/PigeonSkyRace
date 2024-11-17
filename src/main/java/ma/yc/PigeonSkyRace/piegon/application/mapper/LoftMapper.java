package ma.yc.PigeonSkyRace.piegon.application.mapper;

import ma.yc.PigeonSkyRace.piegon.application.dto.request.LoftRequestDTO;
import ma.yc.PigeonSkyRace.piegon.application.dto.response.LoftResponseDTO;
import ma.yc.PigeonSkyRace.piegon.domain.model.entity.Loft;
import ma.yc.PigeonSkyRace.user.domain.model.valueobject.UserId;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface LoftMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "user", source = "userId", qualifiedByName = "stringToUserId")
    Loft toEntity ( LoftRequestDTO loftRequestDTO );

    @Mapping(target = "id", expression = "java(loft.getId().toHexString())")
    @Mapping(target = "userId", expression = "java(loft.getUser().toHexString())")
    @Mapping(source = "createdDate", target = "createdDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    LoftResponseDTO toDto ( Loft loft );

    @Named("stringToUserId")
    default UserId stringToUserId ( String userId ) {
        if (userId == null) {
            return null;
        }
        return new UserId(new ObjectId(userId));
    }
}
