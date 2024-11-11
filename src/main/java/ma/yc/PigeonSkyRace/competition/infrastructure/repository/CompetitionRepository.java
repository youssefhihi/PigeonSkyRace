package ma.yc.PigeonSkyRace.competition.infrastructure.repository;

import ma.yc.PigeonSkyRace.competition.domain.ValueObject.CompetitionId;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.Coordinate;
import ma.yc.PigeonSkyRace.competition.domain.entity.Competition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CompetitionRepository extends MongoRepository<Competition, CompetitionId> {

    @Query("{ 'id': ?0 }")
    @Update("{ '$set': { 'coordinate': ?1 }}")
   void updateCoordinateById(CompetitionId id, Coordinate coordinate);
    Optional<Competition> findFirstByStartTimeBeforeAndEndTimeAfter(LocalDateTime startTime, LocalDateTime endTime);

}
