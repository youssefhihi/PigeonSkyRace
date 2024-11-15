package ma.yc.PigeonSkyRace.serviceTest;

import ma.yc.PigeonSkyRace.common.application.service.PasswordEncoder;
import ma.yc.PigeonSkyRace.common.domain.exception.NotFoundException;
import ma.yc.PigeonSkyRace.user.application.dto.request.LoginRequestDTO;
import ma.yc.PigeonSkyRace.user.application.dto.request.RegisterRequestDTO;
import ma.yc.PigeonSkyRace.user.application.dto.response.AuthResponseDTO;
import ma.yc.PigeonSkyRace.user.application.mapper.UserMapper;
import ma.yc.PigeonSkyRace.user.domain.enums.Role;
import ma.yc.PigeonSkyRace.user.domain.exception.InvalidCredentialsException;
import ma.yc.PigeonSkyRace.user.domain.exception.UserAlreadyExistsException;
import ma.yc.PigeonSkyRace.user.domain.model.aggregate.User;
import ma.yc.PigeonSkyRace.user.domain.model.valueobject.UserId;
import ma.yc.PigeonSkyRace.user.domain.service.impl.DefaultUserDomainService;
import ma.yc.PigeonSkyRace.user.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Default User Domain Service Test")
public class UserDomainServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private DefaultUserDomainService userService;

    private User createTestUser(String id) {
        User user = new User();
        if (id != null) {
            user.setId(UserId.fromString(id));
        }
        user.setName("Soufiane");
        user.setUsername("soufiane123");
        user.setEmail("soufiane@gmail.com");
        user.setPassword("encodedPassword");
        user.setRole(Role.breeder);
        user.setLofts(new ArrayList<>());
        return user;
    }

    @Nested
    @DisplayName("findAll() method tests")
    class FindAll {
        @Test
        @DisplayName("should return all users if they exist")
        void shouldReturn_AllUsers () {
            List<User> users = List.of(createTestUser(new UserId().toHexString()), createTestUser(new UserId().toHexString()));

            when(userRepository.findAll()).thenReturn(users);

            when(userMapper.toDto(any(User.class))).thenAnswer(invocation -> {
                User user = invocation.getArgument(0, User.class);
                return new AuthResponseDTO(user.getId().toHexString(), user.getName(), user.getEmail(), user.getUsername(), user.getRole());
            });

            List<AuthResponseDTO> result = userService.findAll();

            assertEquals(users.size(), result.size());
            assertEquals(users.get(0).getEmail(), result.get(0).email());
            verify(userRepository).findAll();
        }

        @Test
        @DisplayName("should return no users if none exist")
        void shouldReturn_NoUsers () {
            when(userRepository.findAll()).thenReturn(List.of());

            List<AuthResponseDTO> result = userService.findAll();

            assertTrue(result.isEmpty());
            verify(userRepository).findAll();
        }
    }

    @Nested
    @DisplayName("findById() method tests")
    class FindById {
        @Test
        @DisplayName("should return user when user exists")
        void shouldReturn_User () {
            UserId id = new UserId();
            User user = createTestUser(id.toHexString());

            when(userRepository.findById(id)).thenReturn(Optional.of(user));
            when(userMapper.toDto(user)).thenReturn(new AuthResponseDTO(user.getId().toHexString(), user.getName(), user.getEmail(), user.getUsername(), user.getRole()));

            AuthResponseDTO result = userService.findById(id);

            assertNotNull(result);
            assertEquals(user.getEmail(), result.email());
            verify(userRepository).findById(id);
        }
    }

    @Nested
    @DisplayName("registerUser() method tests")
    class RegisterUser {
        @Test
        @DisplayName("should register user when data is valid")
        void shouldRegister_User () {
            RegisterRequestDTO request = new RegisterRequestDTO("soufiane", "soufiane123", "soufiane@gmail.com", "securepassword", Role.breeder);

            User user = createTestUser(null);

            assertNotNull(user.getId(), "User ID should not be null");

            when(userMapper.toEntity(request)).thenReturn(user);
            when(userRepository.existsByEmail(request.email())).thenReturn(false);
            when(userRepository.existsByUsername(request.username())).thenReturn(false);
            when(userRepository.save(user)).thenReturn(user);

            when(userMapper.toDto(user)).thenReturn(new AuthResponseDTO(user.getId().toHexString(), user.getName(), user.getEmail(), user.getUsername(), user.getRole()));

            AuthResponseDTO result = userService.registerUser(request);

            assertNotNull(result);
            assertEquals(user.getEmail(), result.email());
            assertEquals(user.getId().toHexString(), result.id());
            verify(userRepository).save(user);
        }

        @Test
        @DisplayName("should throw exception when email already exists")
        void shouldThrowException_WhenEmailExists () {

            RegisterRequestDTO request = new RegisterRequestDTO("Soufiane",
                    "soufiane123",
                    "soufiane@gmail.com",
                    "securepassword",
                    Role.breeder
            );

            when(userRepository.existsByEmail(request.email())).thenReturn(true);

            UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class, () -> userService.registerUser(request));

            assertEquals("Email already exists: " + request.email(), exception.getMessage());
            verify(userRepository, never()).save(any(User.class));
        }

    }

    @Nested
    @DisplayName("login() method tests")
    class Login {
        @Test
        @DisplayName("should login user when credentials are valid")
        void shouldLogin_User() {
            LoginRequestDTO request = new LoginRequestDTO("soufiane@gmail.com", "securepassword");
            User user = createTestUser(new UserId().toHexString());

            when(userRepository.findUserByEmail(request.email())).thenReturn(Optional.of(user));
            when(passwordEncoder.matches(request.password(), user.getPassword())).thenReturn(true); // Mock password match
            when(userMapper.toDto(user)).thenReturn(new AuthResponseDTO(user.getId().toHexString(), user.getName(), user.getEmail(), user.getUsername(), user.getRole()));

            AuthResponseDTO result = userService.login(request);

            assertNotNull(result);
            assertEquals(user.getEmail(), result.email());
            verify(userRepository).findUserByEmail(request.email());
            verify(passwordEncoder).matches(request.password(), user.getPassword()); // Verify password matching
        }

        @Test
        @DisplayName("should throw exception when credentials are invalid")
        void shouldThrowException_WhenInvalidCredentials() {
            LoginRequestDTO request = new LoginRequestDTO("soufiane@gmail.com", "wrongpassword");
            when(userRepository.findUserByEmail(request.email())).thenReturn(Optional.empty());

            InvalidCredentialsException exception = assertThrows(InvalidCredentialsException.class, () -> userService.login(request));

            assertEquals("Invalid email or password", exception.getMessage());
            verify(userRepository).findUserByEmail(request.email());
            verify(passwordEncoder, never()).matches(anyString(), anyString()); // Ensure password matching isn't called
        }
    }
}
