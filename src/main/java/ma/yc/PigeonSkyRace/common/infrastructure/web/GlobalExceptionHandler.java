package ma.yc.PigeonSkyRace.common.infrastructure.web;

import ma.yc.PigeonSkyRace.common.domain.exception.NotFoundException;
import ma.yc.PigeonSkyRace.competition.domain.Exception.FailedToRegister;
import ma.yc.PigeonSkyRace.piegon.domain.exception.InvalidLoftException;
import ma.yc.PigeonSkyRace.piegon.domain.exception.InvalidLoftIdFormatException;
import ma.yc.PigeonSkyRace.user.domain.exception.InvalidCredentialsException;
import ma.yc.PigeonSkyRace.user.domain.exception.UserRegistrationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse>handleNotFoundException(NotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(),HttpStatus.BAD_REQUEST.value(), "Not Found",e.getMessage() );
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidLoftIdFormatException.class)
    public ResponseEntity<String> handleInvalidLoftIdFormatException ( InvalidLoftIdFormatException ex ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(InvalidLoftException.class)
    public ResponseEntity<String> handleInvalidLoftException ( InvalidLoftException ex ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(UserRegistrationException.class)
    public ResponseEntity<ErrorResponse> handleUserRegistrationException ( UserRegistrationException ex ) {
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Registration Error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationExceptions ( MethodArgumentNotValidException ex ) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream().findFirst().map(FieldError::getDefaultMessage).orElse("Validation Error");

        return new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Validation Error", errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException ( Exception ex ) {
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialsException ( InvalidCredentialsException e ) {
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(), "Authentication Error", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
    @ExceptionHandler(FailedToRegister.class)
    public ResponseEntity<ErrorResponse> handleRegisterFailed ( FailedToRegister e ) {
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(), "register Error", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

}

record ErrorResponse(LocalDateTime timestamp, int status, String error, String message) {
}