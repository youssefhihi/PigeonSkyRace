package ma.yc.PigeonSkyRace.result.domain.service;

import ma.yc.PigeonSkyRace.competition.application.dto.response.CompetitionResponseDto;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.CompetitionId;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.CompetitionPigeonId;
import ma.yc.PigeonSkyRace.competition.domain.entity.Competition;
import ma.yc.PigeonSkyRace.result.application.dto.request.ResultRequestDto;
import ma.yc.PigeonSkyRace.result.application.dto.response.ResultResponseDto;

import java.util.List;


public interface ResultService {
    ResultResponseDto createResult(ResultRequestDto requestDto, CompetitionResponseDto competition);
    List<ResultResponseDto> calculatePoint(CompetitionPigeonId id);
}
