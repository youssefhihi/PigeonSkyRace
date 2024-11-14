package ma.yc.PigeonSkyRace.result.domain.service;

import ma.yc.PigeonSkyRace.competition.application.dto.response.CompetitionResponseDto;
import ma.yc.PigeonSkyRace.competition.domain.entity.Competition;
import ma.yc.PigeonSkyRace.result.application.dto.request.ResultRequestDto;
import ma.yc.PigeonSkyRace.result.application.dto.response.ResultResponseDto;


public interface ResultService {
    ResultResponseDto createResult(ResultRequestDto requestDto, CompetitionResponseDto competition);
    ResultResponseDto calculatePoint();
}
