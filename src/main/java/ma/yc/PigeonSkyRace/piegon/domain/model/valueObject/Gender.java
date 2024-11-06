package ma.yc.PigeonSkyRace.piegon.domain.model.valueObject;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Gender(@NotNull @NotBlank String gender) {
    public Gender {
        if (!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female")) {
            throw new IllegalArgumentException("The gender must be either 'male' or 'female'.");
        }
    }
}
