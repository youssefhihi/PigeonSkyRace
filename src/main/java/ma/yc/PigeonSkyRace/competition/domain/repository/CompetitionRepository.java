package ma.yc.PigeonSkyRace.competition.domain.repository;

import ma.yc.PigeonSkyRace.competition.domain.entity.Competition;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.UUID;

public interface CompetitionRepository extends MongoRepository<Competition, UUID> {
}
