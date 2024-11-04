package ma.yc.PigeonSkyRace.user.domain.valueObject;

import com.fasterxml.jackson.annotation.JsonValue;
import org.bson.types.ObjectId;

public record UserId(ObjectId value) {
    public UserId() {
        this(new ObjectId());
    }

    @JsonValue
    public String toHexString() {
        return value.toHexString();
    }
}
