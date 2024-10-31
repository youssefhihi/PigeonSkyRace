package ma.yc.PigeonSkyRace.competition.domain.ValueObject;


import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CompetitionId(@NotNull UUID competitionId) {

}
