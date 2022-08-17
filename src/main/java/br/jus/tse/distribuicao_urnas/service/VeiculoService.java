package br.jus.tse.distribuicao_urnas.service;

import br.jus.tse.distribuicao_urnas.domain.Veiculo;
import br.jus.tse.distribuicao_urnas.model.VeiculoDTO;
import br.jus.tse.distribuicao_urnas.repos.VeiculoRepository;
import br.jus.tse.distribuicao_urnas.util.WebUtils;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoService(final VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public List<VeiculoDTO> findAll() {
        return veiculoRepository.findAll(Sort.by("id"))
                .stream()
                .map(veiculo -> mapToDTO(veiculo, new VeiculoDTO()))
                .collect(Collectors.toList());
    }

    public VeiculoDTO get(final Long id) {
        return veiculoRepository.findById(id)
                .map(veiculo -> mapToDTO(veiculo, new VeiculoDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final VeiculoDTO veiculoDTO) {
        final Veiculo veiculo = new Veiculo();
        mapToEntity(veiculoDTO, veiculo);
        return veiculoRepository.save(veiculo).getId();
    }

    public void update(final Long id, final VeiculoDTO veiculoDTO) {
        final Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(veiculoDTO, veiculo);
        veiculoRepository.save(veiculo);
    }

    public void delete(final Long id) {
        veiculoRepository.deleteById(id);
    }

    private VeiculoDTO mapToDTO(final Veiculo veiculo, final VeiculoDTO veiculoDTO) {
        veiculoDTO.setId(veiculo.getId());
        veiculoDTO.setDescricao(veiculo.getDescricao());
        veiculoDTO.setPlaca(veiculo.getPlaca());
        veiculoDTO.setCapacidade(veiculo.getCapacidade());
        return veiculoDTO;
    }

    private Veiculo mapToEntity(final VeiculoDTO veiculoDTO, final Veiculo veiculo) {
        veiculo.setDescricao(veiculoDTO.getDescricao());
        veiculo.setPlaca(veiculoDTO.getPlaca());
        veiculo.setCapacidade(veiculoDTO.getCapacidade());
        return veiculo;
    }

    @Transactional
    public String getReferencedWarning(final Long id) {
        final Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!veiculo.getVeiculoPlanoRotas().isEmpty()) {
            return WebUtils.getMessage("veiculo.planoRota.manyToOne.referenced", veiculo.getVeiculoPlanoRotas().iterator().next().getId());
        } else if (!veiculo.getVeiculoSimulacaoSimulacaos().isEmpty()) {
            return WebUtils.getMessage("veiculo.simulacao.manyToMany.referenced", veiculo.getVeiculoSimulacaoSimulacaos().iterator().next().getId());
        }
        return null;
    }

}
