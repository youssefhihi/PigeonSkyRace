package ma.yc.PigeonSkyRace.user.domain.repository;

import ma.yc.PigeonSkyRace.user.domain.entity.User;
import ma.yc.PigeonSkyRace.user.domain.valueObject.UserId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UserId> {
    Optional<User> findUserByEmail( String email);
    boolean existsByEmail(String email);
}
