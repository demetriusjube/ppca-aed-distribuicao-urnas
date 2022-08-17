package br.jus.tse.distribuicao_urnas.rest;

import br.jus.tse.distribuicao_urnas.model.RestricaoSimulacaoDTO;
import br.jus.tse.distribuicao_urnas.service.RestricaoSimulacaoService;
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
@RequestMapping(value = "/api/restricaoSimulacaos", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestricaoSimulacaoResource {

    private final RestricaoSimulacaoService restricaoSimulacaoService;

    public RestricaoSimulacaoResource(final RestricaoSimulacaoService restricaoSimulacaoService) {
        this.restricaoSimulacaoService = restricaoSimulacaoService;
    }

    @GetMapping
    public ResponseEntity<List<RestricaoSimulacaoDTO>> getAllRestricaoSimulacaos() {
        return ResponseEntity.ok(restricaoSimulacaoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestricaoSimulacaoDTO> getRestricaoSimulacao(
            @PathVariable final Long id) {
        return ResponseEntity.ok(restricaoSimulacaoService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createRestricaoSimulacao(
            @RequestBody @Valid final RestricaoSimulacaoDTO restricaoSimulacaoDTO) {
        return new ResponseEntity<>(restricaoSimulacaoService.create(restricaoSimulacaoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRestricaoSimulacao(@PathVariable final Long id,
            @RequestBody @Valid final RestricaoSimulacaoDTO restricaoSimulacaoDTO) {
        restricaoSimulacaoService.update(id, restricaoSimulacaoDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteRestricaoSimulacao(@PathVariable final Long id) {
        restricaoSimulacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
