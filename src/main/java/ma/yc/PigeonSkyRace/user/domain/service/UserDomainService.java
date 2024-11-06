package ma.yc.PigeonSkyRace.user.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.PigeonSkyRace.common.application.service.PasswordEncoder;
import ma.yc.PigeonSkyRace.user.application.dto.request.LoginRequest;
import ma.yc.PigeonSkyRace.user.application.dto.request.RegisterRequest;
import ma.yc.PigeonSkyRace.user.application.dto.response.LoginResponse;
import ma.yc.PigeonSkyRace.user.application.mapper.UserMapper;
import ma.yc.PigeonSkyRace.user.domain.exception.InvalidCredentialsException;
import ma.yc.PigeonSkyRace.user.domain.exception.UserAlreadyExistsException;
import ma.yc.PigeonSkyRace.user.domain.exception.UserRegistrationException;
import ma.yc.PigeonSkyRace.user.domain.model.aggregate.User;
import ma.yc.PigeonSkyRace.user.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDomainService {

    private final UserRepository repository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public User registerUser ( RegisterRequest registerRequest ) {
        try {
            validateNewUser(registerRequest);

            User user = userMapper.toEntity(registerRequest);
            if (user == null) {
                throw new UserRegistrationException("Error mapping user data");
            }

            user.setPassword(passwordEncoder.encode(registerRequest.password()));

            User savedUser = repository.save(user);
            log.info("User successfully registered: {}", savedUser.getEmail());

            return savedUser;
        } catch (Exception e) {
            log.error("Error during user registration: {}", e.getMessage(), e);
            throw new UserRegistrationException("Failed to register user: " + e.getMessage());
        }
    }

    public LoginResponse login ( LoginRequest loginRequest ) {
        User user = repository.findUserByEmail(loginRequest.email()).orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        return new LoginResponse(user.getEmail(), user.getUserName(), user.getRole(), "Login successful");
    }


    private void validateNewUser ( RegisterRequest registerRequest ) {
        if (registerRequest == null) {
            throw new UserRegistrationException("User data cannot be null");
        }

        if (repository.existsByEmail(registerRequest.email())) {
            throw new UserAlreadyExistsException("Email already exists: " + registerRequest.email());
        }
        if (repository.existsByUserName(registerRequest.userName())) {
            throw new UserAlreadyExistsException("Username already exists: " + registerRequest.userName());
        }
    }
}
