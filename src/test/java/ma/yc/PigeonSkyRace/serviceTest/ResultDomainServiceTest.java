package ma.yc.PigeonSkyRace.serviceTest;

import ma.yc.PigeonSkyRace.competition.application.dto.response.CompetitionResponseDto;
import ma.yc.PigeonSkyRace.competition.application.mapping.CompetitionMapper;
import ma.yc.PigeonSkyRace.competition.application.service.CompetitionPigeonApplicationService;
import ma.yc.PigeonSkyRace.competition.application.service.SeasonApplicationService;
import ma.yc.PigeonSkyRace.competition.application.service.SeasonPigeonApplicationService;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.AdmissionPercentage;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.Coordinate;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonId;
import ma.yc.PigeonSkyRace.competition.domain.entity.CompetitionPigeon;
import ma.yc.PigeonSkyRace.competition.domain.entity.SeasonPigeon;
import ma.yc.PigeonSkyRace.piegon.application.service.LoftApplicationService;
import ma.yc.PigeonSkyRace.piegon.application.service.PigeonApplicationService;
import ma.yc.PigeonSkyRace.piegon.domain.model.aggregate.Pigeon;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.BandNumber;
import ma.yc.PigeonSkyRace.result.application.dto.request.ResultRequestDto;
import ma.yc.PigeonSkyRace.result.application.dto.response.ResultResponseDto;
import ma.yc.PigeonSkyRace.result.application.mapping.ResultMapper;
import ma.yc.PigeonSkyRace.result.domain.entity.Result;
import ma.yc.PigeonSkyRace.result.domain.service.impl.ResultDomainService;
import ma.yc.PigeonSkyRace.result.domain.valueObject.ResultId;
import ma.yc.PigeonSkyRace.result.infrastructure.repository.ResultRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Result Domain Service Test")
public class ResultDomainServiceTest {

    @Mock
    private ResultRepository resultRepository;
    @Mock
    private PigeonApplicationService pigeonApplicationService;
    @Mock
    private ResultMapper resultMapper;
    @Mock
    private SeasonPigeonApplicationService seasonPigeonApplicationService;
    @Mock
    private SeasonApplicationService seasonApplicationService;
    @Mock
    private CompetitionPigeonApplicationService competitionPigeonApplicationService;
    @Mock
    private LoftApplicationService loftApplicationService;
    @Mock
    private CompetitionMapper competitionMapper;

    @InjectMocks
    private ResultDomainService resultService;

    @Nested
    @DisplayName("createResult() method tests")
    class CreateResult {

        @Test
        @DisplayName("should create and return result successfully")
        void shouldCreateResult() {
            ResultRequestDto request = new ResultRequestDto(
                    new BandNumber("AU 2023 XYZ 3234534"),
                    LocalDateTime.now()
            );

            CompetitionResponseDto competition = new CompetitionResponseDto(
                    null, "Race1", "Description", new Coordinate(1.21332, 2.11232),
                    100, 500.0, new AdmissionPercentage(12.2), new SeasonId(),
                    LocalDateTime.now(), LocalDateTime.now().plusHours(1), LocalDateTime.now()
            );

            Result result = new Result();
            ResultResponseDto response = new ResultResponseDto(
                    new ResultId(), 500.0, 1200.0, 100.0,
                    new BandNumber("AU 2021 XYZ 3234534"),
                    "Loft A", LocalDateTime.now(), LocalDateTime.now().plusHours(2)
            );

            when(pigeonApplicationService.findPigeonByBandNumber(request.bandNumber()))
                    .thenReturn(mock(Pigeon.class));
            when(seasonPigeonApplicationService.findSeasonPigeonPigeonAndSeason(any(), any()))
                    .thenReturn(mock(SeasonPigeon.class));
            when(competitionPigeonApplicationService.findBySeasonPigeonAndCompetition(any(), any()))
                    .thenReturn(mock(CompetitionPigeon.class));
            when(resultMapper.toEntity(request)).thenReturn(result);
            when(loftApplicationService.geLoftCoordinate(any())).thenReturn(mock(Coordinate.class));
            when(resultMapper.toDto(any(Result.class))).thenReturn(response);
            when(resultRepository.save(any(Result.class))).thenReturn(result);

            ResultResponseDto resultResponse = resultService.createResult(request, competition);

            assertNotNull(resultResponse);
            assertEquals(response.distance(), resultResponse.distance());
            verify(resultRepository).save(any(Result.class));
        }
    }

    @Nested
    @DisplayName("calculatePoint() method tests")
    class CalculatePoint {

        @Test
        @DisplayName("should calculate and assign points successfully")
        void shouldCalculatePoints() {
            CompetitionResponseDto competition = new CompetitionResponseDto(
                    null, "Race1", "Description", null,
                    100, 500.0, null, null,
                    LocalDateTime.now(), LocalDateTime.now().plusHours(1), LocalDateTime.now()
            );

            CompetitionPigeon cp1 = mock(CompetitionPigeon.class);
            CompetitionPigeon cp2 = mock(CompetitionPigeon.class);
            Result result1 = new Result();
            Result result2 = new Result();

            result1.setSpeed(1200.0);
            result2.setSpeed(1100.0);

            when(competitionPigeonApplicationService.findByCompetition(any()))
                    .thenReturn(List.of(cp1, cp2));
            when(resultRepository.findByCompetitionPigeon(cp1)).thenReturn(result1);
            when(resultRepository.findByCompetitionPigeon(cp2)).thenReturn(result2);

            List<ResultResponseDto> results = resultService.calculatePoint(competition);

            assertEquals(2, results.size());
            verify(resultRepository, times(2)).save(any(Result.class));
        }
    }
}
