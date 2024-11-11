package ma.yc.PigeonSkyRace.result.infrastructure.repository;

import ma.yc.PigeonSkyRace.result.domain.entity.Result;
import ma.yc.PigeonSkyRace.result.domain.valueObject.ResultId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResultRepository extends MongoRepository<Result, ResultId> {
}
