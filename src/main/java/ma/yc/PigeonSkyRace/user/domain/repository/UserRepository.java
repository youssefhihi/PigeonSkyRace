package ma.yc.PigeonSkyRace.user.domain.repository;

import ma.yc.PigeonSkyRace.user.domain.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {
}
