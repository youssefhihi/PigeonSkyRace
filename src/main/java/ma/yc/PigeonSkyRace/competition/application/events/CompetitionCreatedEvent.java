package ma.yc.PigeonSkyRace.competition.application.events;

import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonId;
import ma.yc.PigeonSkyRace.competition.domain.entity.Competition;

public class CompetitionCreatedEvent {
    private final Competition competition;
    private final SeasonId seasonId;

    public CompetitionCreatedEvent(Competition competition, SeasonId seasonId) {
        this.competition = competition;
        this.seasonId = seasonId;
    }

    public Competition getCompetition() {
        return competition;
    }

    public SeasonId getSeasonId() {
        return seasonId;
    }
}
