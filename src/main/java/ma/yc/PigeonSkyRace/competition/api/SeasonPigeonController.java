package ma.yc.PigeonSkyRace.competition.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.PigeonSkyRace.common.infrastructure.web.ResponseApi;
import ma.yc.PigeonSkyRace.competition.application.dto.request.SeasonPigeonRequestDTO;
import ma.yc.PigeonSkyRace.competition.application.dto.response.SeasonPigeonResponseDTO;
import ma.yc.PigeonSkyRace.competition.domain.service.SeasonPigeonDomainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/season-pigeons")
@Validated
class SeasonPigeonController {

    private final SeasonPigeonDomainService service;

    @PostMapping
    public ResponseEntity<ResponseApi<SeasonPigeonResponseDTO>> associatePigeonWithSeason ( @Valid @RequestBody SeasonPigeonRequestDTO dto ) {
        SeasonPigeonResponseDTO createdSeasonPigeon = service.associatePigeonWithSeason(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseApi.created(createdSeasonPigeon, "Pigeon was successfully associated with the season"));
    }
}
