package ma.yc.PigeonSkyRace.common.domain.exception;


import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonId;

public class NotFoundException  extends RuntimeException {
    public <T> NotFoundException(String entity, T id) {
        super(entity + " with id " + id + " not found");
    }


}