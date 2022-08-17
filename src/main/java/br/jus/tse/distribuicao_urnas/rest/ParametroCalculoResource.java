package br.jus.tse.distribuicao_urnas.rest;

import br.jus.tse.distribuicao_urnas.model.ParametroCalculoDTO;
import br.jus.tse.distribuicao_urnas.service.ParametroCalculoService;
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
@RequestMapping(value = "/api/parametroCalculos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ParametroCalculoResource {

    private final ParametroCalculoService parametroCalculoService;

    public ParametroCalculoResource(final ParametroCalculoService parametroCalculoService) {
        this.parametroCalculoService = parametroCalculoService;
    }

    @GetMapping
    public ResponseEntity<List<ParametroCalculoDTO>> getAllParametroCalculos() {
        return ResponseEntity.ok(parametroCalculoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParametroCalculoDTO> getParametroCalculo(@PathVariable final Long id) {
        return ResponseEntity.ok(parametroCalculoService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createParametroCalculo(
            @RequestBody @Valid final ParametroCalculoDTO parametroCalculoDTO) {
        return new ResponseEntity<>(parametroCalculoService.create(parametroCalculoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateParametroCalculo(@PathVariable final Long id,
            @RequestBody @Valid final ParametroCalculoDTO parametroCalculoDTO) {
        parametroCalculoService.update(id, parametroCalculoDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteParametroCalculo(@PathVariable final Long id) {
        parametroCalculoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
