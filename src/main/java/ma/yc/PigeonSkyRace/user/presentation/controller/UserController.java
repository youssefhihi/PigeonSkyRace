package ma.yc.PigeonSkyRace.user.presentation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.PigeonSkyRace.user.application.dto.request.UserDto;
import ma.yc.PigeonSkyRace.user.domain.entity.User;
import ma.yc.PigeonSkyRace.user.domain.service.UserDomainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserDomainService service;
    @PostMapping("/register")
    public ResponseEntity<User> registerUser( @Valid @RequestBody UserDto userDto) {
        User registeredUser = service.registerUser(userDto);
        return ResponseEntity.ok(registeredUser);
    }

}
