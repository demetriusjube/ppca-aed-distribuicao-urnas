package br.jus.tse.distribuicao_urnas.rest;

import br.jus.tse.distribuicao_urnas.model.LocalVotacaoDTO;
import br.jus.tse.distribuicao_urnas.service.LocalVotacaoService;
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
@RequestMapping(value = "/api/localVotacaos", produces = MediaType.APPLICATION_JSON_VALUE)
public class LocalVotacaoResource {

    private final LocalVotacaoService localVotacaoService;

    public LocalVotacaoResource(final LocalVotacaoService localVotacaoService) {
        this.localVotacaoService = localVotacaoService;
    }

    @GetMapping
    public ResponseEntity<List<LocalVotacaoDTO>> getAllLocalVotacaos() {
        return ResponseEntity.ok(localVotacaoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalVotacaoDTO> getLocalVotacao(@PathVariable final Long id) {
        return ResponseEntity.ok(localVotacaoService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createLocalVotacao(
            @RequestBody @Valid final LocalVotacaoDTO localVotacaoDTO) {
        return new ResponseEntity<>(localVotacaoService.create(localVotacaoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLocalVotacao(@PathVariable final Long id,
            @RequestBody @Valid final LocalVotacaoDTO localVotacaoDTO) {
        localVotacaoService.update(id, localVotacaoDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteLocalVotacao(@PathVariable final Long id) {
        localVotacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
