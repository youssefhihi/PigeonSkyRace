package ma.yc.PigeonSkyRace.result.domain.valueObject;

import com.fasterxml.jackson.annotation.JsonValue;
import org.bson.types.ObjectId;

public record ResultId(ObjectId value) {
    public ResultId () {
        this(new ObjectId());
    }

    @JsonValue
    public String toHexString () {
        return value.toHexString();
    }

    public static ResultId fromString (String id ) {
        return new ResultId(new ObjectId(id));
    }
}
