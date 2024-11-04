package ma.yc.PigeonSkyRace.user.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.PigeonSkyRace.common.application.service.PasswordEncoder;
import ma.yc.PigeonSkyRace.user.application.dto.request.UserDto;
import ma.yc.PigeonSkyRace.user.application.mapper.UserMapper;
import ma.yc.PigeonSkyRace.user.domain.entity.User;
import ma.yc.PigeonSkyRace.user.domain.exception.UserAlreadyExistsException;
import ma.yc.PigeonSkyRace.user.domain.exception.UserRegistrationException;
import ma.yc.PigeonSkyRace.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDomainService {

    private final UserRepository repository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public User registerUser ( UserDto userDto ) {
        try {
            validateNewUser(userDto);

            User user = userMapper.toEntity(userDto);
            if (user == null) {
                throw new UserRegistrationException("Error mapping user data");
            }

            user.setPassword(passwordEncoder.encode(userDto.password()));

            User savedUser = repository.save(user);
            log.info("User successfully registered: {}", savedUser.getEmail());

            return savedUser;
        } catch (Exception e) {
            log.error("Error during user registration: {}", e.getMessage(), e);
            throw new UserRegistrationException("Failed to register user: " + e.getMessage());
        }
    }

    private void validateNewUser ( UserDto userDto ) {
        if (userDto == null) {
            throw new UserRegistrationException("User data cannot be null");
        }

        if (repository.existsByEmail(userDto.email())) {
            throw new UserAlreadyExistsException("Email already exists: " + userDto.email());
        }
    }
}
