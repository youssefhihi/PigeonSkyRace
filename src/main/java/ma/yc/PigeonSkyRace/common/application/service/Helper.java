package ma.yc.PigeonSkyRace.common.application.service;

import ma.yc.PigeonSkyRace.competition.domain.ValueObject.Coordinate;
import org.springframework.stereotype.Component;

@Component
public class Helper {

    public static double calculateDistance(Coordinate loftCoordinate, Coordinate competitionCoordinate) {

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

        return 6371.01 * c;
    }
}
