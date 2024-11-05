package ma.yc.PigeonSkyRace.user.domain.exception;

public class UserAlreadyExistsException extends  RuntimeException{
    public UserAlreadyExistsException(String message){
        super(message);
    }
}
