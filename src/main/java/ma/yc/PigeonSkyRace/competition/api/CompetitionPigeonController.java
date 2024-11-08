package ma.yc.PigeonSkyRace.competition.api;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.PigeonSkyRace.common.infrastructure.web.ApiResponse;
import ma.yc.PigeonSkyRace.competition.application.dto.request.CompetitionPigeonRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.CompetitionPigeonResponseDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.CompetitionResponseDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.SeasonPigeonResponseDto;
import ma.yc.PigeonSkyRace.competition.application.mapping.CompetitionMapper;
import ma.yc.PigeonSkyRace.competition.application.mapping.SeasonPigeonMapper;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.CompetitionId;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonPigeonId;
import ma.yc.PigeonSkyRace.competition.domain.entity.Competition;
import ma.yc.PigeonSkyRace.competition.domain.entity.SeasonPigeon;
import ma.yc.PigeonSkyRace.competition.domain.service.CompetitionPigeonService;
import ma.yc.PigeonSkyRace.competition.domain.service.CompetitionService;
import ma.yc.PigeonSkyRace.competition.domain.service.SeasonPigeonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/competition/register")
@Slf4j
@RequiredArgsConstructor
public class CompetitionPigeonController {

    private final CompetitionPigeonService service;
    private final SeasonPigeonService seasonPigeonService;
    private final CompetitionService competitionService;
    private final CompetitionMapper competitionMapper;
    private final SeasonPigeonMapper seasonPigeonMapper;
    private static final Logger logger = LoggerFactory.getLogger(CompetitionPigeonController.class);


    @PostMapping("/{competitionId}")
    public ResponseEntity<ApiResponse<CompetitionPigeonResponseDto>> registerToCompetition(
            @PathVariable String competitionId,
            @RequestAttribute String seasonPigeonId) {

        Competition competition = competitionMapper.toEntity(competitionService.getCompetition(CompetitionId.fromString(competitionId)));
        SeasonPigeon seasonPigeon = seasonPigeonMapper.toEntity(seasonPigeonService.getSeasonById(SeasonPigeonId.fromString(seasonPigeonId)));

        CompetitionPigeonResponseDto responseDto = service.registerToCompetition(seasonPigeon, competition);

        ApiResponse<CompetitionPigeonResponseDto> response = new ApiResponse<>(
                responseDto,
                "Pigeon registered successfully to competition : " + responseDto.competition().getName(),
                HttpStatus.CREATED);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}