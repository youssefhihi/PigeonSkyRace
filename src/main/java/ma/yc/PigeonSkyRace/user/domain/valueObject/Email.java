package ma.yc.PigeonSkyRace.user.domain.valueObject;


import jakarta.validation.constraints.NotNull;

public record Email(@NotNull String email) {
    public Email {
        if (email == null || !email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
    }
}
