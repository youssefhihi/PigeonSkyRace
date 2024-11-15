package ma.yc.PigeonSkyRace.result.domain.service;

import com.itextpdf.text.DocumentException;
import ma.yc.PigeonSkyRace.result.application.dto.response.ResultResponseDto;

import java.util.List;

public interface PdfGenerationService {
    byte[] generateResultsPdf(List<ResultResponseDto> results) throws DocumentException;
}