package ma.yc.PigeonSkyRace.result.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.PigeonSkyRace.common.infrastructure.web.ApiResponse;
import ma.yc.PigeonSkyRace.common.infrastructure.web.ResponseApi;
import ma.yc.PigeonSkyRace.competition.application.dto.response.CompetitionPigeonResponseDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.CompetitionResponseDto;
import ma.yc.PigeonSkyRace.competition.application.service.CompetitionApplicationService;
import ma.yc.PigeonSkyRace.competition.application.service.CompetitionPigeonApplicationService;
import ma.yc.PigeonSkyRace.competition.application.service.SeasonApplicationService;
import ma.yc.PigeonSkyRace.competition.application.service.SeasonPigeonApplicationService;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.CompetitionId;
import ma.yc.PigeonSkyRace.competition.domain.entity.Competition;
import ma.yc.PigeonSkyRace.competition.domain.entity.CompetitionPigeon;
import ma.yc.PigeonSkyRace.competition.domain.entity.SeasonPigeon;
import ma.yc.PigeonSkyRace.piegon.application.dto.request.LoftRequestDTO;
import ma.yc.PigeonSkyRace.piegon.application.dto.response.LoftResponseDTO;
import ma.yc.PigeonSkyRace.piegon.application.service.PigeonApplicationService;
import ma.yc.PigeonSkyRace.piegon.domain.model.aggregate.Pigeon;
import ma.yc.PigeonSkyRace.result.application.dto.request.ResultRequestDto;
import ma.yc.PigeonSkyRace.result.application.dto.response.ResultResponseDto;
import ma.yc.PigeonSkyRace.result.domain.service.ResultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
@Validated
public class ResultController {
    private final ResultService resultService;
    private final CompetitionApplicationService competitionApplicationService;



    @PostMapping("{competitionId}")
    public ResponseEntity<ResponseApi<ResultResponseDto>> createResult(
            @PathVariable String competitionId,
            @Valid @RequestBody ResultRequestDto resultRequestDto) {

        CompetitionResponseDto competitionResponseDto = competitionApplicationService.getCompetition(CompetitionId.fromString(competitionId));
       ResultResponseDto responseDto = resultService.createResult(resultRequestDto, competitionResponseDto);

        ResponseApi<ResultResponseDto> response = new ResponseApi<>(
                responseDto,
                "The information stored with success for competition : " + responseDto.competitionPigeon().getCompetition().getName(),
                HttpStatus.CREATED
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
}