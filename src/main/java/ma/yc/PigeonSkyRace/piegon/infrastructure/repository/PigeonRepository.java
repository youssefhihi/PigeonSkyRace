package ma.yc.PigeonSkyRace.piegon.infrastructure.repository;

import ma.yc.PigeonSkyRace.piegon.domain.model.aggregate.Pigeon;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PigeonRepository extends MongoRepository<Pigeon, UUID> {
}
