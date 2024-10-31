package ma.yc.PigeonSkyRace.piegon.domain.repository;

import ma.yc.PigeonSkyRace.piegon.domain.entity.Pigeon;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PigeonRepository extends MongoRepository<Pigeon, UUID> {
}
