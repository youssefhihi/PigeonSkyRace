package ma.yc.PigeonSkyRace.user.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.PigeonSkyRace.user.application.dto.request.LoginRequest;
import ma.yc.PigeonSkyRace.user.application.dto.request.RegisterRequest;
import ma.yc.PigeonSkyRace.user.application.dto.response.LoginResponse;
import ma.yc.PigeonSkyRace.user.domain.model.aggregate.User;
import ma.yc.PigeonSkyRace.user.domain.service.UserDomainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserDomainService service;
    @PostMapping("/register")
    public ResponseEntity<User> registerUser( @Valid @RequestBody RegisterRequest registerRequest ) {
        User registeredUser = service.registerUser(registerRequest);
        return ResponseEntity.ok(registeredUser);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse response = service.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("he");
    }
}
