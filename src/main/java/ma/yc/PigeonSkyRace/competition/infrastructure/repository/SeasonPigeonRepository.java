package ma.yc.PigeonSkyRace.competition.infrastructure.repository;

import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonId;
import ma.yc.PigeonSkyRace.competition.domain.entity.SeasonPigeon;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SeasonPigeonRepository extends MongoRepository<SeasonPigeon, SeasonId> {
}
