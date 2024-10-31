package ma.yc.PigeonSkyRace.competition.domain.repository;

import ma.yc.PigeonSkyRace.competition.domain.entity.Season;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface SeasonRepository extends MongoRepository<Season, UUID> {
}
