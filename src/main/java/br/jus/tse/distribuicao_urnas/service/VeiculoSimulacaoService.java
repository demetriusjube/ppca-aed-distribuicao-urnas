package br.jus.tse.distribuicao_urnas.service;

import br.jus.tse.distribuicao_urnas.domain.PlanoRota;
import br.jus.tse.distribuicao_urnas.domain.Simulacao;
import br.jus.tse.distribuicao_urnas.domain.Veiculo;
import br.jus.tse.distribuicao_urnas.domain.VeiculoSimulacao;
import br.jus.tse.distribuicao_urnas.model.VeiculoSimulacaoDTO;
import br.jus.tse.distribuicao_urnas.repos.PlanoRotaRepository;
import br.jus.tse.distribuicao_urnas.repos.SimulacaoRepository;
import br.jus.tse.distribuicao_urnas.repos.VeiculoRepository;
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
public class VeiculoSimulacaoService {

    private final VeiculoSimulacaoRepository veiculoSimulacaoRepository;
    private final SimulacaoRepository simulacaoRepository;
    private final VeiculoRepository veiculoRepository;
    private final PlanoRotaRepository planoRotaRepository;

    public VeiculoSimulacaoService(final VeiculoSimulacaoRepository veiculoSimulacaoRepository,
            final SimulacaoRepository simulacaoRepository,
            final VeiculoRepository veiculoRepository,
            final PlanoRotaRepository planoRotaRepository) {
        this.veiculoSimulacaoRepository = veiculoSimulacaoRepository;
        this.simulacaoRepository = simulacaoRepository;
        this.veiculoRepository = veiculoRepository;
        this.planoRotaRepository = planoRotaRepository;
    }

    public List<VeiculoSimulacaoDTO> findAll() {
        return veiculoSimulacaoRepository.findAll(Sort.by("id"))
                .stream()
                .map(veiculoSimulacao -> mapToDTO(veiculoSimulacao, new VeiculoSimulacaoDTO()))
                .collect(Collectors.toList());
    }

    public VeiculoSimulacaoDTO get(final Long id) {
        return veiculoSimulacaoRepository.findById(id)
                .map(veiculoSimulacao -> mapToDTO(veiculoSimulacao, new VeiculoSimulacaoDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final VeiculoSimulacaoDTO veiculoSimulacaoDTO) {
        final VeiculoSimulacao veiculoSimulacao = new VeiculoSimulacao();
        mapToEntity(veiculoSimulacaoDTO, veiculoSimulacao);
        return veiculoSimulacaoRepository.save(veiculoSimulacao).getId();
    }

    public void update(final Long id, final VeiculoSimulacaoDTO veiculoSimulacaoDTO) {
        final VeiculoSimulacao veiculoSimulacao = veiculoSimulacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(veiculoSimulacaoDTO, veiculoSimulacao);
        veiculoSimulacaoRepository.save(veiculoSimulacao);
    }

    public void delete(final Long id) {
        veiculoSimulacaoRepository.deleteById(id);
    }

    private VeiculoSimulacaoDTO mapToDTO(final VeiculoSimulacao veiculoSimulacao,
            final VeiculoSimulacaoDTO veiculoSimulacaoDTO) {
        veiculoSimulacaoDTO.setId(veiculoSimulacao.getId());
        veiculoSimulacaoDTO.setUrnasTransportadas(veiculoSimulacao.getUrnasTransportadas());
        veiculoSimulacaoDTO.setSimulacao(veiculoSimulacao.getSimulacao() == null ? null : veiculoSimulacao.getSimulacao().getId());
        veiculoSimulacaoDTO.setVeiculo(veiculoSimulacao.getVeiculo() == null ? null : veiculoSimulacao.getVeiculo().getId());
        veiculoSimulacaoDTO.setPlanoRota(veiculoSimulacao.getPlanoRota() == null ? null : veiculoSimulacao.getPlanoRota().getId());
        return veiculoSimulacaoDTO;
    }

    private VeiculoSimulacao mapToEntity(final VeiculoSimulacaoDTO veiculoSimulacaoDTO,
            final VeiculoSimulacao veiculoSimulacao) {
        veiculoSimulacao.setUrnasTransportadas(veiculoSimulacaoDTO.getUrnasTransportadas());
        final Simulacao simulacao = veiculoSimulacaoDTO.getSimulacao() == null ? null : simulacaoRepository.findById(veiculoSimulacaoDTO.getSimulacao())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "simulacao not found"));
        veiculoSimulacao.setSimulacao(simulacao);
        final Veiculo veiculo = veiculoSimulacaoDTO.getVeiculo() == null ? null : veiculoRepository.findById(veiculoSimulacaoDTO.getVeiculo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "veiculo not found"));
        veiculoSimulacao.setVeiculo(veiculo);
        final PlanoRota planoRota = veiculoSimulacaoDTO.getPlanoRota() == null ? null : planoRotaRepository.findById(veiculoSimulacaoDTO.getPlanoRota())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "planoRota not found"));
        veiculoSimulacao.setPlanoRota(planoRota);
        return veiculoSimulacao;
    }

    @Transactional
    public String getReferencedWarning(final Long id) {
        final VeiculoSimulacao veiculoSimulacao = veiculoSimulacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return null;
    }

}
