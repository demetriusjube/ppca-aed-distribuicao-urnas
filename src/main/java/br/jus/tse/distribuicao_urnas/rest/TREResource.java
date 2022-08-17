package br.jus.tse.distribuicao_urnas.rest;

import br.jus.tse.distribuicao_urnas.model.TREDTO;
import br.jus.tse.distribuicao_urnas.service.TREService;
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
@RequestMapping(value = "/api/tREs", produces = MediaType.APPLICATION_JSON_VALUE)
public class TREResource {

    private final TREService tREService;

    public TREResource(final TREService tREService) {
        this.tREService = tREService;
    }

    @GetMapping
    public ResponseEntity<List<TREDTO>> getAllTREs() {
        return ResponseEntity.ok(tREService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TREDTO> getTRE(@PathVariable final Long id) {
        return ResponseEntity.ok(tREService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createTRE(@RequestBody @Valid final TREDTO tREDTO) {
        return new ResponseEntity<>(tREService.create(tREDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTRE(@PathVariable final Long id,
            @RequestBody @Valid final TREDTO tREDTO) {
        tREService.update(id, tREDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteTRE(@PathVariable final Long id) {
        tREService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
