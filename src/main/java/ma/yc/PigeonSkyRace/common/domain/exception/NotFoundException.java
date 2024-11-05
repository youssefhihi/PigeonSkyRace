package ma.yc.PigeonSkyRace.common.domain.exception;


import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonId;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String entity, SeasonId id) {
        super(entity + " with id " + id + " not found");
    }


}