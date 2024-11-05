package ma.yc.PigeonSkyRace.piegon.api;


import lombok.RequiredArgsConstructor;
import ma.yc.PigeonSkyRace.piegon.application.dto.request.LoftRequestDTO;
import ma.yc.PigeonSkyRace.piegon.application.dto.response.LoftResponseDTO;
import ma.yc.PigeonSkyRace.piegon.domain.model.entity.Loft;
import ma.yc.PigeonSkyRace.piegon.domain.service.LoftDomainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lofts")
@RequiredArgsConstructor
public class LoftController {

    private final LoftDomainService service;

    @PostMapping("/create")
    public ResponseEntity<LoftResponseDTO> createLoft ( @RequestBody LoftRequestDTO loftRequest ) {
        LoftResponseDTO response = service.createLoft(loftRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
