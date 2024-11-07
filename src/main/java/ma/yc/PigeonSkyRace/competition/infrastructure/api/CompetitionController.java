package ma.yc.PigeonSkyRace.competition.infrastructure.api;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import ma.yc.PigeonSkyRace.common.infrastructure.web.ApiResponse;
import ma.yc.PigeonSkyRace.competition.application.dto.request.CompetitionRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.CompetitionResponseDto;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.CompetitionId;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.Coordinate;
import ma.yc.PigeonSkyRace.competition.domain.service.CompetitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/competition")
@Slf4j
public class CompetitionController {

    private final CompetitionService competitionService;
    private static final Logger logger = LoggerFactory.getLogger(CompetitionController.class);


    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }


    @PostMapping
    public ResponseEntity<ApiResponse<CompetitionResponseDto>> createCompetition(@Valid @RequestBody CompetitionRequestDto competition) {
        CompetitionResponseDto createdCompetition = competitionService.createCompetition(competition);
        ApiResponse<CompetitionResponseDto> response = new ApiResponse<>(createdCompetition, "Competition created successfully", HttpStatus.CREATED);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<CompetitionResponseDto>> getAllCompetitions() {
        List<CompetitionResponseDto> competitions = competitionService.getAllCompetitions();
        return new ResponseEntity<>(competitions, HttpStatus.OK);
    }





}