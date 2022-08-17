package br.jus.tse.distribuicao_urnas.rest;

import br.jus.tse.distribuicao_urnas.model.CentroDistribuicaoDTO;
import br.jus.tse.distribuicao_urnas.service.CentroDistribuicaoService;
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
@RequestMapping(value = "/api/centroDistribuicaos", produces = MediaType.APPLICATION_JSON_VALUE)
public class CentroDistribuicaoResource {

    private final CentroDistribuicaoService centroDistribuicaoService;

    public CentroDistribuicaoResource(final CentroDistribuicaoService centroDistribuicaoService) {
        this.centroDistribuicaoService = centroDistribuicaoService;
    }

    @GetMapping
    public ResponseEntity<List<CentroDistribuicaoDTO>> getAllCentroDistribuicaos() {
        return ResponseEntity.ok(centroDistribuicaoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CentroDistribuicaoDTO> getCentroDistribuicao(
            @PathVariable final Long id) {
        return ResponseEntity.ok(centroDistribuicaoService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createCentroDistribuicao(
            @RequestBody @Valid final CentroDistribuicaoDTO centroDistribuicaoDTO) {
        return new ResponseEntity<>(centroDistribuicaoService.create(centroDistribuicaoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCentroDistribuicao(@PathVariable final Long id,
            @RequestBody @Valid final CentroDistribuicaoDTO centroDistribuicaoDTO) {
        centroDistribuicaoService.update(id, centroDistribuicaoDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteCentroDistribuicao(@PathVariable final Long id) {
        centroDistribuicaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
