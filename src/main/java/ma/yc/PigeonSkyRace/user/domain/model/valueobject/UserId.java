package ma.yc.PigeonSkyRace.user.domain.model.valueobject;

import com.fasterxml.jackson.annotation.JsonValue;
import org.bson.types.ObjectId;

public record UserId(ObjectId value) {
    public UserId () {
        this(new ObjectId());
    }

    public static UserId fromString ( String id ) {
        return new UserId(new ObjectId(id));
    }

    @JsonValue
    public String toHexString () {
        return value.toHexString();
    }
}
