package ma.yc.PigeonSkyRace.result.api;

import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.PigeonSkyRace.common.infrastructure.web.ResponseApi;
import ma.yc.PigeonSkyRace.competition.application.dto.response.CompetitionResponseDto;
import ma.yc.PigeonSkyRace.competition.application.service.CompetitionApplicationService;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.CompetitionId;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.CompetitionPigeonId;
import ma.yc.PigeonSkyRace.result.application.dto.request.ResultRequestDto;
import ma.yc.PigeonSkyRace.result.application.dto.response.ResultResponseDto;
import ma.yc.PigeonSkyRace.result.domain.service.PdfGenerationService;
import ma.yc.PigeonSkyRace.result.domain.service.ResultService;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@Validated
@RequestMapping("/api/v1/results")
public class ResultController {
    private final ResultService resultService;
    private final CompetitionApplicationService competitionApplicationService;
    private final PdfGenerationService pdfGenerationService;


    @PostMapping("/{competitionId}")
    public ResponseEntity<ResponseApi<ResultResponseDto>> createResult ( @PathVariable String competitionId, @RequestBody ResultRequestDto resultRequestDto ) {

        CompetitionResponseDto competitionResponseDto = competitionApplicationService.getCompetition(CompetitionId.fromString(competitionId));
        ResultResponseDto responseDto = resultService.createResult(resultRequestDto, competitionResponseDto);

        ResponseApi<ResultResponseDto> response = new ResponseApi<>(responseDto, "The information stored with success for competition ", HttpStatus.CREATED);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ResultResponseDto>> getResult ( @PathVariable String id ) {
        List<ResultResponseDto> resultsResponseDto = resultService.calculatePoint(CompetitionPigeonId.fromString(id));

        return new ResponseEntity<>(resultsResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> downloadResultPdf ( @PathVariable String id ) {
        try {
            List<ResultResponseDto> results = resultService.calculatePoint(CompetitionPigeonId.fromString(id));

            byte[] pdfBytes = pdfGenerationService.generateResultsPdf(results);

            // Set up response headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename("race-results.pdf").build());

            return ResponseEntity.ok().headers(headers).body(pdfBytes);
        } catch (DocumentException e) {
            log.error("Error generating PDF: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}