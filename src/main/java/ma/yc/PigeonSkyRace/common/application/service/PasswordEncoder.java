package ma.yc.PigeonSkyRace.common.application.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoder {
    private final BCryptPasswordEncoder encoder;

    public PasswordEncoder () {
        this.encoder = new BCryptPasswordEncoder();
    }

    public String encode ( String password ) {
        return encoder.encode(password);
    }

    public boolean matches ( String rawPassword, String encodedPassword ) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}