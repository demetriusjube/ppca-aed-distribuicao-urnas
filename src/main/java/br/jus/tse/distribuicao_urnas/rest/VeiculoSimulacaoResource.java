package br.jus.tse.distribuicao_urnas.rest;

import br.jus.tse.distribuicao_urnas.model.VeiculoSimulacaoDTO;
import br.jus.tse.distribuicao_urnas.service.VeiculoSimulacaoService;
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
@RequestMapping(value = "/api/veiculoSimulacaos", produces = MediaType.APPLICATION_JSON_VALUE)
public class VeiculoSimulacaoResource {

    private final VeiculoSimulacaoService veiculoSimulacaoService;

    public VeiculoSimulacaoResource(final VeiculoSimulacaoService veiculoSimulacaoService) {
        this.veiculoSimulacaoService = veiculoSimulacaoService;
    }

    @GetMapping
    public ResponseEntity<List<VeiculoSimulacaoDTO>> getAllVeiculoSimulacaos() {
        return ResponseEntity.ok(veiculoSimulacaoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoSimulacaoDTO> getVeiculoSimulacao(@PathVariable final Long id) {
        return ResponseEntity.ok(veiculoSimulacaoService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createVeiculoSimulacao(
            @RequestBody @Valid final VeiculoSimulacaoDTO veiculoSimulacaoDTO) {
        return new ResponseEntity<>(veiculoSimulacaoService.create(veiculoSimulacaoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVeiculoSimulacao(@PathVariable final Long id,
            @RequestBody @Valid final VeiculoSimulacaoDTO veiculoSimulacaoDTO) {
        veiculoSimulacaoService.update(id, veiculoSimulacaoDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteVeiculoSimulacao(@PathVariable final Long id) {
        veiculoSimulacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
