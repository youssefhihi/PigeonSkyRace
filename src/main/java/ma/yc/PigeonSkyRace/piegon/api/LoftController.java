package ma.yc.PigeonSkyRace.piegon.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.yc.PigeonSkyRace.common.infrastructure.web.ResponseApi;
import ma.yc.PigeonSkyRace.piegon.application.dto.request.LoftRequestDTO;
import ma.yc.PigeonSkyRace.piegon.application.dto.response.LoftResponseDTO;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.LoftId;
import ma.yc.PigeonSkyRace.piegon.domain.service.impl.DefaultLoftDomainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lofts")
@RequiredArgsConstructor
class LoftController {

    private final DefaultLoftDomainService service;

    @PostMapping()
    public ResponseEntity<ResponseApi<LoftResponseDTO>> create ( @Valid @RequestBody LoftRequestDTO loftRequest ) {
        LoftResponseDTO response = service.create(loftRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseApi.created(response, "Loft created successfully"));
    }

    @GetMapping
    public ResponseEntity<ResponseApi<List<LoftResponseDTO>>> findAll(){
        List<LoftResponseDTO> loftResponse = service.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseApi.success(loftResponse , "List of Lofts retrieved with success "));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseApi<LoftResponseDTO>> findById(@Valid @PathVariable String id){
        LoftResponseDTO loftResponse = service.findById(LoftId.fromString(id));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseApi.success(loftResponse , "Loft with id " + id + " retrieved with success"));
    }
}
