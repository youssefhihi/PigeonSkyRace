package ma.yc.PigeonSkyRace.user.application.dto.response;

import ma.yc.PigeonSkyRace.user.domain.enums.Role;


public record LoginResponse(String email, String userName, Role role, String message) {
}