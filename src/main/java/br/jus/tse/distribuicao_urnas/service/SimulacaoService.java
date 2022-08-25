package br.jus.tse.distribuicao_urnas.service;

import br.jus.tse.distribuicao_urnas.domain.CentroDistribuicao;
import br.jus.tse.distribuicao_urnas.domain.Simulacao;
import br.jus.tse.distribuicao_urnas.domain.VeiculoSimulacao;
import br.jus.tse.distribuicao_urnas.model.SimulacaoDTO;
import br.jus.tse.distribuicao_urnas.repos.CentroDistribuicaoRepository;
import br.jus.tse.distribuicao_urnas.repos.SimulacaoRepository;
import br.jus.tse.distribuicao_urnas.repos.VeiculoSimulacaoRepository;
import br.jus.tse.distribuicao_urnas.util.WebUtils;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class SimulacaoService {

    private final SimulacaoRepository simulacaoRepository;
    private final CentroDistribuicaoRepository centroDistribuicaoRepository;
    private final VeiculoSimulacaoRepository veiculoSimulacaoRepository;

    public SimulacaoService(final SimulacaoRepository simulacaoRepository,
            final CentroDistribuicaoRepository centroDistribuicaoRepository,
            final VeiculoSimulacaoRepository veiculoSimulacaoRepository) {
        this.simulacaoRepository = simulacaoRepository;
        this.centroDistribuicaoRepository = centroDistribuicaoRepository;
        this.veiculoSimulacaoRepository = veiculoSimulacaoRepository;
    }

    public List<SimulacaoDTO> findAll() {
        return simulacaoRepository.findAll(Sort.by("id"))
                .stream()
                .map(simulacao -> mapToDTO(simulacao, new SimulacaoDTO()))
                .collect(Collectors.toList());
    }

    public SimulacaoDTO get(final Long id) {
        return simulacaoRepository.findById(id)
                .map(simulacao -> mapToDTO(simulacao, new SimulacaoDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final SimulacaoDTO simulacaoDTO) {
        final Simulacao simulacao = new Simulacao();
        mapToEntity(simulacaoDTO, simulacao);
        return simulacaoRepository.save(simulacao).getId();
    }

    public void update(final Long id, final SimulacaoDTO simulacaoDTO) {
        final Simulacao simulacao = simulacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(simulacaoDTO, simulacao);
        simulacaoRepository.save(simulacao);
    }

    public void delete(final Long id) {
        simulacaoRepository.deleteById(id);
    }

    private SimulacaoDTO mapToDTO(final Simulacao simulacao, final SimulacaoDTO simulacaoDTO) {
        simulacaoDTO.setId(simulacao.getId());
        simulacaoDTO.setDescricao(simulacao.getDescricao());
        simulacaoDTO.setDataHora(simulacao.getDataHora());
        simulacaoDTO.setTipoOtimizacao(simulacao.getTipoOtimizacao());
        simulacaoDTO.setCentroDistribuicao(simulacao.getCentroDistribuicao() == null ? null : simulacao.getCentroDistribuicao().getId());
        return simulacaoDTO;
    }

    private Simulacao mapToEntity(final SimulacaoDTO simulacaoDTO, final Simulacao simulacao) {
        simulacao.setDescricao(simulacaoDTO.getDescricao());
        simulacao.setDataHora(simulacaoDTO.getDataHora());
        simulacao.setTipoOtimizacao(simulacaoDTO.getTipoOtimizacao());
        final CentroDistribuicao centroDistribuicao = simulacaoDTO.getCentroDistribuicao() == null ? null : centroDistribuicaoRepository.findById(simulacaoDTO.getCentroDistribuicao())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "centroDistribuicao not found"));
        simulacao.setCentroDistribuicao(centroDistribuicao);
        return simulacao;
    }

    @Transactional
    public String getReferencedWarning(final Long id) {
        final Simulacao simulacao = simulacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!simulacao.getRestricoesRestricaoSimulacaos().isEmpty()) {
            return WebUtils.getMessage("simulacao.restricaoSimulacao.oneToMany.referenced", simulacao.getRestricoesRestricaoSimulacaos().iterator().next().getId());
        } else if (!simulacao.getSimulacaoVeiculoSimulacaos().isEmpty()) {
            return WebUtils.getMessage("simulacao.veiculoSimulacao.manyToOne.referenced", simulacao.getSimulacaoVeiculoSimulacaos().iterator().next().getId());
        }
        return null;
    }

}
