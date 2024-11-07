package ma.yc.PigeonSkyRace.competition.infrastructure.api;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.PigeonSkyRace.competition.domain.service.SeasonPigeonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/season/register")
@Slf4j
@RequiredArgsConstructor
public class SeasonPigeonController {
    private final SeasonPigeonService seasonPigeonService;
    private static final Logger logger = LoggerFactory.getLogger(SeasonPigeonController.class);

//    @PostMapping("/{seasonId}")
//    public ResponseEntity<ApiResponse<SeasonPigeonResponseDto>> registerToSeason( @PathVariable String seasonId,
//                                                                                  @RequestAttribute String pigeonId){
//
//    }
}
