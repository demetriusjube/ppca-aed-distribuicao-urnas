package br.jus.tse.distribuicao_urnas.service;

import br.jus.tse.distribuicao_urnas.domain.RestricaoSimulacao;
import br.jus.tse.distribuicao_urnas.domain.Simulacao;
import br.jus.tse.distribuicao_urnas.model.RestricaoSimulacaoDTO;
import br.jus.tse.distribuicao_urnas.repos.RestricaoSimulacaoRepository;
import br.jus.tse.distribuicao_urnas.repos.SimulacaoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class RestricaoSimulacaoService {

    private final RestricaoSimulacaoRepository restricaoSimulacaoRepository;
    private final SimulacaoRepository simulacaoRepository;

    public RestricaoSimulacaoService(
            final RestricaoSimulacaoRepository restricaoSimulacaoRepository,
            final SimulacaoRepository simulacaoRepository) {
        this.restricaoSimulacaoRepository = restricaoSimulacaoRepository;
        this.simulacaoRepository = simulacaoRepository;
    }

    public List<RestricaoSimulacaoDTO> findAll() {
        return restricaoSimulacaoRepository.findAll(Sort.by("id"))
                .stream()
                .map(restricaoSimulacao -> mapToDTO(restricaoSimulacao, new RestricaoSimulacaoDTO()))
                .collect(Collectors.toList());
    }

    public RestricaoSimulacaoDTO get(final Long id) {
        return restricaoSimulacaoRepository.findById(id)
                .map(restricaoSimulacao -> mapToDTO(restricaoSimulacao, new RestricaoSimulacaoDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final RestricaoSimulacaoDTO restricaoSimulacaoDTO) {
        final RestricaoSimulacao restricaoSimulacao = new RestricaoSimulacao();
        mapToEntity(restricaoSimulacaoDTO, restricaoSimulacao);
        return restricaoSimulacaoRepository.save(restricaoSimulacao).getId();
    }

    public void update(final Long id, final RestricaoSimulacaoDTO restricaoSimulacaoDTO) {
        final RestricaoSimulacao restricaoSimulacao = restricaoSimulacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(restricaoSimulacaoDTO, restricaoSimulacao);
        restricaoSimulacaoRepository.save(restricaoSimulacao);
    }

    public void delete(final Long id) {
        restricaoSimulacaoRepository.deleteById(id);
    }

    private RestricaoSimulacaoDTO mapToDTO(final RestricaoSimulacao restricaoSimulacao,
            final RestricaoSimulacaoDTO restricaoSimulacaoDTO) {
        restricaoSimulacaoDTO.setId(restricaoSimulacao.getId());
        restricaoSimulacaoDTO.setValor(restricaoSimulacao.getValor());
        restricaoSimulacaoDTO.setTipoRestricao(restricaoSimulacao.getTipoRestricao());
        restricaoSimulacaoDTO.setRestricoes(restricaoSimulacao.getRestricoes() == null ? null : restricaoSimulacao.getRestricoes().getId());
        return restricaoSimulacaoDTO;
    }

    private RestricaoSimulacao mapToEntity(final RestricaoSimulacaoDTO restricaoSimulacaoDTO,
            final RestricaoSimulacao restricaoSimulacao) {
        restricaoSimulacao.setValor(restricaoSimulacaoDTO.getValor());
        restricaoSimulacao.setTipoRestricao(restricaoSimulacaoDTO.getTipoRestricao());
        final Simulacao restricoes = restricaoSimulacaoDTO.getRestricoes() == null ? null : simulacaoRepository.findById(restricaoSimulacaoDTO.getRestricoes())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "restricoes not found"));
        restricaoSimulacao.setRestricoes(restricoes);
        return restricaoSimulacao;
    }

}
