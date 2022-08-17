package br.jus.tse.distribuicao_urnas.rest;

import br.jus.tse.distribuicao_urnas.model.VeiculoDTO;
import br.jus.tse.distribuicao_urnas.service.VeiculoService;
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
@RequestMapping(value = "/api/veiculos", produces = MediaType.APPLICATION_JSON_VALUE)
public class VeiculoResource {

    private final VeiculoService veiculoService;

    public VeiculoResource(final VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping
    public ResponseEntity<List<VeiculoDTO>> getAllVeiculos() {
        return ResponseEntity.ok(veiculoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> getVeiculo(@PathVariable final Long id) {
        return ResponseEntity.ok(veiculoService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createVeiculo(@RequestBody @Valid final VeiculoDTO veiculoDTO) {
        return new ResponseEntity<>(veiculoService.create(veiculoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVeiculo(@PathVariable final Long id,
            @RequestBody @Valid final VeiculoDTO veiculoDTO) {
        veiculoService.update(id, veiculoDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable final Long id) {
        veiculoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
