package ma.yc.PigeonSkyRace.user.application.service;

import ma.yc.PigeonSkyRace.user.application.dto.response.AuthResponseDTO;
import ma.yc.PigeonSkyRace.user.domain.model.valueobject.UserId;

public interface UserApplicationService {
    AuthResponseDTO getById ( UserId id );
}
