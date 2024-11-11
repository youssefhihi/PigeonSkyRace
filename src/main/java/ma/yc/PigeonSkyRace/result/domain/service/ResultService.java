package ma.yc.PigeonSkyRace.result.domain.service;

import ma.yc.PigeonSkyRace.result.application.dto.request.ResultRequestDto;
import ma.yc.PigeonSkyRace.result.application.dto.response.ResultResponseDto;


public interface ResultService {
    ResultResponseDto createResult(ResultRequestDto requestDto);
}
