package ma.yc.PigeonSkyRace.serviceTest;

import ma.yc.PigeonSkyRace.competition.domain.ValueObject.Coordinate;
import ma.yc.PigeonSkyRace.piegon.application.dto.request.CoordinateRequestDTO;
import ma.yc.PigeonSkyRace.piegon.application.dto.request.LoftRequestDTO;
import ma.yc.PigeonSkyRace.piegon.application.dto.response.LoftResponseDTO;
import ma.yc.PigeonSkyRace.piegon.application.mapper.LoftMapper;
import ma.yc.PigeonSkyRace.piegon.domain.model.entity.Loft;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.LoftId;
import ma.yc.PigeonSkyRace.piegon.domain.service.LoftNameGenerator;
import ma.yc.PigeonSkyRace.piegon.domain.service.impl.DefaultLoftDomainService;
import ma.yc.PigeonSkyRace.piegon.infrastructure.repository.LoftRepository;
import ma.yc.PigeonSkyRace.user.domain.model.valueobject.UserId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Default Loft Domain Service Test")
public class LoftDomainServiceTest {

    @Mock
    private LoftRepository loftRepository;

    @Mock
    private LoftMapper loftMapper;

    @Mock
    private LoftNameGenerator loftNameGenerator;

    @InjectMocks
    private DefaultLoftDomainService loftService;

    private Loft createTestLoft ( String id ) {
        Loft loft = new Loft();
        if (id != null) {
            loft.setId(LoftId.fromString(id));
        }
        loft.setName("Loft A");
        loft.setCoordinate(new Coordinate(40.7128, 74.0060));
        loft.setUser(new UserId());
        return loft;
    }

    @Nested
    @DisplayName("findAll() method tests")
    class FindAll {

        @Test
        @DisplayName("should return all lofts if they exist")
        void shouldReturn_AllLofts () {

            Loft loft1 = createTestLoft(new LoftId().toHexString());
            Loft loft2 = createTestLoft(new LoftId().toHexString());

            loft1.setCreatedDate(LocalDateTime.now());
            loft2.setCreatedDate(LocalDateTime.now());

            List<Loft> lofts = List.of(loft1, loft2);

            when(loftRepository.findAll()).thenReturn(lofts);

            when(loftMapper.toDto(any(Loft.class))).thenAnswer(invocation -> {
                Loft loft = invocation.getArgument(0, Loft.class);
                CoordinateRequestDTO coordinateRequestDTO = new CoordinateRequestDTO(loft.getCoordinate().latitude(), loft.getCoordinate().longitude());
                return new LoftResponseDTO(loft.getId().toHexString(), loft.getName(), coordinateRequestDTO, loft.getUser().toHexString(), loft.getCreatedDate().toString());
            });

            List<LoftResponseDTO> result = loftService.findAll();

            assertEquals(lofts.size(), result.size());
            assertEquals(lofts.get(0).getName(), result.get(0).name());
            assertEquals(lofts.get(0).getCoordinate().latitude(), result.get(0).coordinate().latitude());
            assertEquals(lofts.get(0).getCoordinate().longitude(), result.get(0).coordinate().longitude());
            assertNotNull(result.get(0).createdDate());

            verify(loftRepository).findAll();
        }
    }


    @Test
    @DisplayName("should return no lofts if none exist")
    void shouldReturn_NoLofts () {
        when(loftRepository.findAll()).thenReturn(List.of());
        List<LoftResponseDTO> result = loftService.findAll();
        assertTrue(result.isEmpty());
        verify(loftRepository).findAll();
    }


    @Nested
    @DisplayName("findById() method tests")
    class FindById {

        @Test
        @DisplayName("should return loft when loft exists")
        void shouldReturn_Loft () {
            LoftId id = new LoftId();
            Loft loft = createTestLoft(id.toHexString());

            when(loftRepository.findById(id)).thenReturn(Optional.of(loft));

            when(loftMapper.toDto(loft)).thenAnswer(invocation -> {
                CoordinateRequestDTO coordinateRequestDTO = new CoordinateRequestDTO(loft.getCoordinate().latitude(), loft.getCoordinate().longitude());

                return new LoftResponseDTO(loft.getId().toHexString(), loft.getName(), coordinateRequestDTO, loft.getUser().toHexString(), loft.getCreatedDate() != null ? loft.getCreatedDate().toString() : null);
            });

            LoftResponseDTO result = loftService.findById(id);

            assertNotNull(result);
            assertEquals(loft.getName(), result.name());
            assertEquals(loft.getCoordinate().latitude(), result.coordinate().latitude());
            assertEquals(loft.getCoordinate().longitude(), result.coordinate().longitude());

            verify(loftRepository).findById(id);
        }


    }

    @Nested
    @DisplayName("create() method tests")
    class Create {
        @Test
        @DisplayName("should create loft when data is valid")
        void shouldCreate_Loft () {
            CoordinateRequestDTO coordinateRequestDTO = new CoordinateRequestDTO(34.0522, 118.2437);
            LoftRequestDTO request = new LoftRequestDTO(coordinateRequestDTO, "user-id");

            Loft loft = createTestLoft(null);
            loft.setName("LOFT-12345");  // Set expected name
            when(loftMapper.toEntity(request)).thenReturn(loft);
            when(loftNameGenerator.generateUniqueLoftName()).thenReturn("LOFT-12345");
            when(loftRepository.save(loft)).thenReturn(loft);
            when(loftMapper.toDto(loft)).thenReturn(new LoftResponseDTO(loft.getId().toHexString(), loft.getName(), new CoordinateRequestDTO(loft.getCoordinate().latitude(), loft.getCoordinate().longitude()), loft.getUser().toHexString(), LocalDateTime.now().toString()));
            LoftResponseDTO result = loftService.create(request);
            assertNotNull(result);
            assertEquals("LOFT-12345", result.name());
            verify(loftRepository).save(loft);
        }


    }

    @Nested
    @DisplayName("geLoftCoordinate() method tests")
    class GeLoftCoordinate {
        @Test
        @DisplayName("should return loft coordinate when loft exists")
        void shouldReturn_LoftCoordinate () {
            LoftId loftId = new LoftId();
            Loft loft = createTestLoft(loftId.toHexString());
            Coordinate coordinate = loft.getCoordinate();

            when(loftRepository.findById(loftId)).thenReturn(Optional.of(loft));

            Coordinate result = loftService.geLoftCoordinate(loftId);

            assertNotNull(result);
            assertEquals(coordinate, result);
            verify(loftRepository).findById(loftId);
        }


    }
}
