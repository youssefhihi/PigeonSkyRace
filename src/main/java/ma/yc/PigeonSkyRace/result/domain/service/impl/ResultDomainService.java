package ma.yc.PigeonSkyRace.result.domain.service.impl;

import lombok.RequiredArgsConstructor;
import ma.yc.PigeonSkyRace.competition.application.service.CompetitionApplicationService;
import ma.yc.PigeonSkyRace.competition.application.service.CompetitionPigeonApplicationService;
import ma.yc.PigeonSkyRace.competition.application.service.SeasonApplicationService;
import ma.yc.PigeonSkyRace.competition.application.service.SeasonPigeonApplicationService;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.Coordinate;
import ma.yc.PigeonSkyRace.competition.domain.entity.Competition;
import ma.yc.PigeonSkyRace.competition.domain.entity.CompetitionPigeon;
import ma.yc.PigeonSkyRace.competition.domain.entity.SeasonPigeon;
import ma.yc.PigeonSkyRace.piegon.application.service.LoftApplicationService;
import ma.yc.PigeonSkyRace.piegon.application.service.PigeonApplicationService;
import ma.yc.PigeonSkyRace.piegon.domain.model.aggregate.Pigeon;
import ma.yc.PigeonSkyRace.result.application.dto.request.ResultRequestDto;
import ma.yc.PigeonSkyRace.result.application.dto.response.ResultResponseDto;
import ma.yc.PigeonSkyRace.result.application.mapping.ResultMapper;
import ma.yc.PigeonSkyRace.result.domain.entity.Result;
import ma.yc.PigeonSkyRace.result.domain.service.ResultService;
import ma.yc.PigeonSkyRace.result.infrastructure.repository.ResultRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class ResultDomainService implements ResultService {
    private final ResultRepository repository;
    private final PigeonApplicationService pigeonApplicationService;
    private final ResultMapper mapper;
    private final SeasonPigeonApplicationService seasonPigeonApplicationService;
    private final SeasonApplicationService seasonApplicationService;
    private final CompetitionApplicationService competitionApplicationService;
    private final CompetitionPigeonApplicationService competitionPigeonApplicationService;
    private final LoftApplicationService loftApplicationService;


    @Override
    public ResultResponseDto createResult(ResultRequestDto requestDto) {
        Competition competition = competitionApplicationService.getCurrentCompetition();
        Pigeon pigeon = pigeonApplicationService.findPigeonByBandNumber(requestDto.bandNumber());
        SeasonPigeon seasonPigeon = seasonPigeonApplicationService.findSeasonPigeonPigeonAndSeason(
                seasonApplicationService.findById(competition.getSeasonId()),
                pigeon);
        CompetitionPigeon competitionPigeon = competitionPigeonApplicationService.findBySeasonPigeonAndCompetition(seasonPigeon,competition);

        Result result = mapper.toEntity(requestDto);
        result.setCompetitionPigeon(competitionPigeon);
        result.setDistance(calculateDistance(loftApplicationService.geLoftCoordinate(pigeon.getLoft()),competition.getCoordinate()));
        Duration flightTime = Duration.between(competition.getDateStart(), result.getDateArrival());
        result.setSpeed(calculateSpeed(result.getDistance(),flightTime));




        return mapper.toDto(repository.save(result));
    }


    private double calculateSpeed(double distance, Duration flightTime) {
        return distance * 1000 / flightTime.toMinutes();
    }

    private static final double EARTH_RADIUS_KM = 6371.01;

    public static double calculateDistance(Coordinate loftCoordinate,Coordinate competitionCoordinate) {
        double loftLatRad = Math.toRadians(loftCoordinate.latitude());
        double loftLonRad = Math.toRadians(loftCoordinate.longitude());
        double competitionLatRad = Math.toRadians(competitionCoordinate.latitude());
        double competitionLonRad = Math.toRadians(competitionCoordinate.longitude());

        double deltaLat = competitionLatRad - loftLatRad;
        double deltaLon = competitionLonRad - loftLonRad;
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(loftLatRad) * Math.cos(competitionLatRad)
                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }
}
