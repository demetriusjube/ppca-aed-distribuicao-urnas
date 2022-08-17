package br.jus.tse.distribuicao_urnas.rest;

import br.jus.tse.distribuicao_urnas.model.PlanoRotaDTO;
import br.jus.tse.distribuicao_urnas.service.PlanoRotaService;
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
@RequestMapping(value = "/api/planoRotas", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlanoRotaResource {

    private final PlanoRotaService planoRotaService;

    public PlanoRotaResource(final PlanoRotaService planoRotaService) {
        this.planoRotaService = planoRotaService;
    }

    @GetMapping
    public ResponseEntity<List<PlanoRotaDTO>> getAllPlanoRotas() {
        return ResponseEntity.ok(planoRotaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoRotaDTO> getPlanoRota(@PathVariable final Long id) {
        return ResponseEntity.ok(planoRotaService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createPlanoRota(
            @RequestBody @Valid final PlanoRotaDTO planoRotaDTO) {
        return new ResponseEntity<>(planoRotaService.create(planoRotaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePlanoRota(@PathVariable final Long id,
            @RequestBody @Valid final PlanoRotaDTO planoRotaDTO) {
        planoRotaService.update(id, planoRotaDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deletePlanoRota(@PathVariable final Long id) {
        planoRotaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
