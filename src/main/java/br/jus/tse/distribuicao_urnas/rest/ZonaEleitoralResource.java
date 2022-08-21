package br.jus.tse.distribuicao_urnas.rest;

import br.jus.tse.distribuicao_urnas.model.ZonaEleitoralDTO;
import br.jus.tse.distribuicao_urnas.service.ZonaEleitoralService;
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
@RequestMapping(value = "/api/zonaEleitorals", produces = MediaType.APPLICATION_JSON_VALUE)
public class ZonaEleitoralResource {

    private final ZonaEleitoralService zonaEleitoralService;

    public ZonaEleitoralResource(final ZonaEleitoralService zonaEleitoralService) {
        this.zonaEleitoralService = zonaEleitoralService;
    }

    @GetMapping
    public ResponseEntity<List<ZonaEleitoralDTO>> getAllZonaEleitorals() {
        return ResponseEntity.ok(zonaEleitoralService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZonaEleitoralDTO> getZonaEleitoral(@PathVariable final Long id) {
        return ResponseEntity.ok(zonaEleitoralService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createZonaEleitoral(
            @RequestBody @Valid final ZonaEleitoralDTO zonaEleitoralDTO) {
        return new ResponseEntity<>(zonaEleitoralService.create(zonaEleitoralDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateZonaEleitoral(@PathVariable final Long id,
            @RequestBody @Valid final ZonaEleitoralDTO zonaEleitoralDTO) {
        zonaEleitoralService.update(id, zonaEleitoralDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteZonaEleitoral(@PathVariable final Long id) {
        zonaEleitoralService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
