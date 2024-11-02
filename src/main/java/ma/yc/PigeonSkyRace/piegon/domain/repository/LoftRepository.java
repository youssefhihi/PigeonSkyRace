package ma.yc.PigeonSkyRace.piegon.domain.repository;

import ma.yc.PigeonSkyRace.piegon.domain.entity.Loft;
import ma.yc.PigeonSkyRace.piegon.domain.entity.Pigeon;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface LoftRepository extends MongoRepository<Loft, UUID> {
}
