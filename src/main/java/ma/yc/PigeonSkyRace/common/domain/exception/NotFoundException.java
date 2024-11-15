package ma.yc.PigeonSkyRace.common.domain.exception;

import ma.yc.PigeonSkyRace.user.domain.model.valueobject.UserId;

public class NotFoundException extends RuntimeException {
    public <T> NotFoundException(String entity, T id) {
        super(entity + " with id " + extractIdValue(id) + " not found");
    }

    private static <T> String extractIdValue(T id) {
        if (id instanceof UserId userId) {
            return userId.toHexString();
        }
        return id.toString();
    }
}
