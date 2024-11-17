package ma.yc.PigeonSkyRace.piegon.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.yc.PigeonSkyRace.common.infrastructure.web.ResponseApi;
import ma.yc.PigeonSkyRace.piegon.application.dto.request.PigeonRequestDTO;
import ma.yc.PigeonSkyRace.piegon.application.dto.response.PigeonResponseDTO;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.PigeonId;
import ma.yc.PigeonSkyRace.piegon.domain.service.PigeonDomainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pigeons")
@RequiredArgsConstructor
class PigeonController {

    private final PigeonDomainService service;

    @PostMapping
    public ResponseEntity<ResponseApi<PigeonResponseDTO>> create ( @Valid @RequestBody PigeonRequestDTO dto ) {
        PigeonResponseDTO createdPigeon = service.create(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseApi.created(createdPigeon, "Pigeon created successfully"));
    }

    @GetMapping
    public ResponseEntity<ResponseApi<List<PigeonResponseDTO>>> findAll () {
        List<PigeonResponseDTO> pigeonResponse = service.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseApi.success(pigeonResponse, "Successfully retrieved the list of pigeons."));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseApi<PigeonResponseDTO>> findById ( @Valid @PathVariable String id ) {
        PigeonResponseDTO pigeonResponse = service.getSeasonById(PigeonId.fromString(id));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseApi.success(pigeonResponse, "Successfully retrieved the pigeon by id."));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete ( @Valid @PathVariable String id ) {
        Boolean dd = service.deletePigeonById(PigeonId.fromString(id));
        if (dd) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Pigeon deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Pigeon could not be deleted.");
    }
}
