package ma.yc.PigeonSkyRace.result.infrastructure.repository;

import ma.yc.PigeonSkyRace.competition.domain.entity.CompetitionPigeon;
import ma.yc.PigeonSkyRace.result.domain.entity.Result;
import ma.yc.PigeonSkyRace.result.domain.valueObject.ResultId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ResultRepository extends MongoRepository<Result, ResultId> {
    Result findByCompetitionPigeon(CompetitionPigeon competitionPigeon);

}
