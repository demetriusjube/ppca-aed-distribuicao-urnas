package br.jus.tse.distribuicao_urnas.rest;

import br.jus.tse.distribuicao_urnas.model.DistanciaDTO;
import br.jus.tse.distribuicao_urnas.service.DistanciaService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/distancias", produces = MediaType.APPLICATION_JSON_VALUE)
public class DistanciaResource {

    private final DistanciaService distanciaService;

    public DistanciaResource(final DistanciaService distanciaService) {
        this.distanciaService = distanciaService;
    }

    @GetMapping
    public ResponseEntity<List<DistanciaDTO>> getAllDistancias() {
        return ResponseEntity.ok(distanciaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DistanciaDTO> getDistancia(@PathVariable final Long id) {
        return ResponseEntity.ok(distanciaService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createDistancia(
            @RequestBody @Valid final DistanciaDTO distanciaDTO) {
        return new ResponseEntity<>(distanciaService.create(distanciaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDistancia(@PathVariable final Long id,
            @RequestBody @Valid final DistanciaDTO distanciaDTO) {
        distanciaService.update(id, distanciaDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteDistancia(@PathVariable final Long id) {
        distanciaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
