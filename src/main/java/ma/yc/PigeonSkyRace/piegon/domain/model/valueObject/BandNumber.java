package ma.yc.PigeonSkyRace.piegon.domain.model.valueObject;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public record BandNumber(@NotBlank String bandNumber) {

    private static final Pattern BAND_NUMBER_PATTERN =
            Pattern.compile("^[A-Z]{5} \\d{4} [A-Z]{8} \\d{10}$");

    public BandNumber {
        if (!BAND_NUMBER_PATTERN.matcher(bandNumber).matches()) {
            throw new IllegalArgumentException("Invalid band number format. Expected format: AU 2003 XYZ 3234534");
        }

        String[] parts = bandNumber.split(" ");
        int year = Integer.parseInt(parts[1]);
        if (year > LocalDateTime.now().getYear() || year < LocalDateTime.now().minusYears(10).getYear()) {
            throw new IllegalArgumentException("Year must be between " + LocalDateTime.now().minusYears(10).getYear()+" and " + LocalDateTime.now().getYear()+ " .");
        }
    }
}