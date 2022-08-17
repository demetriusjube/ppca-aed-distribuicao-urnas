package br.jus.tse.distribuicao_urnas.rest;

import br.jus.tse.distribuicao_urnas.model.SimulacaoDTO;
import br.jus.tse.distribuicao_urnas.service.SimulacaoService;
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
@RequestMapping(value = "/api/simulacaos", produces = MediaType.APPLICATION_JSON_VALUE)
public class SimulacaoResource {

    private final SimulacaoService simulacaoService;

    public SimulacaoResource(final SimulacaoService simulacaoService) {
        this.simulacaoService = simulacaoService;
    }

    @GetMapping
    public ResponseEntity<List<SimulacaoDTO>> getAllSimulacaos() {
        return ResponseEntity.ok(simulacaoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimulacaoDTO> getSimulacao(@PathVariable final Long id) {
        return ResponseEntity.ok(simulacaoService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createSimulacao(
            @RequestBody @Valid final SimulacaoDTO simulacaoDTO) {
        return new ResponseEntity<>(simulacaoService.create(simulacaoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSimulacao(@PathVariable final Long id,
            @RequestBody @Valid final SimulacaoDTO simulacaoDTO) {
        simulacaoService.update(id, simulacaoDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteSimulacao(@PathVariable final Long id) {
        simulacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
