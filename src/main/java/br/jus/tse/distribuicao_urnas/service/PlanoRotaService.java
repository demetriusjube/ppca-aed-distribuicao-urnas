package br.jus.tse.distribuicao_urnas.service;

import br.jus.tse.distribuicao_urnas.domain.PlanoRota;
import br.jus.tse.distribuicao_urnas.domain.Veiculo;
import br.jus.tse.distribuicao_urnas.model.PlanoRotaDTO;
import br.jus.tse.distribuicao_urnas.repos.PlanoRotaRepository;
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
public class PlanoRotaService {

    private final PlanoRotaRepository planoRotaRepository;
    private final VeiculoRepository veiculoRepository;

    public PlanoRotaService(final PlanoRotaRepository planoRotaRepository,
            final VeiculoRepository veiculoRepository) {
        this.planoRotaRepository = planoRotaRepository;
        this.veiculoRepository = veiculoRepository;
    }

    public List<PlanoRotaDTO> findAll() {
        return planoRotaRepository.findAll(Sort.by("id"))
                .stream()
                .map(planoRota -> mapToDTO(planoRota, new PlanoRotaDTO()))
                .collect(Collectors.toList());
    }

    public PlanoRotaDTO get(final Long id) {
        return planoRotaRepository.findById(id)
                .map(planoRota -> mapToDTO(planoRota, new PlanoRotaDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final PlanoRotaDTO planoRotaDTO) {
        final PlanoRota planoRota = new PlanoRota();
        mapToEntity(planoRotaDTO, planoRota);
        return planoRotaRepository.save(planoRota).getId();
    }

    public void update(final Long id, final PlanoRotaDTO planoRotaDTO) {
        final PlanoRota planoRota = planoRotaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(planoRotaDTO, planoRota);
        planoRotaRepository.save(planoRota);
    }

    public void delete(final Long id) {
        planoRotaRepository.deleteById(id);
    }

    private PlanoRotaDTO mapToDTO(final PlanoRota planoRota, final PlanoRotaDTO planoRotaDTO) {
        planoRotaDTO.setId(planoRota.getId());
        planoRotaDTO.setVeiculo(planoRota.getVeiculo() == null ? null : planoRota.getVeiculo().getId());
        return planoRotaDTO;
    }

    private PlanoRota mapToEntity(final PlanoRotaDTO planoRotaDTO, final PlanoRota planoRota) {
        final Veiculo veiculo = planoRotaDTO.getVeiculo() == null ? null : veiculoRepository.findById(planoRotaDTO.getVeiculo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "veiculo not found"));
        planoRota.setVeiculo(veiculo);
        return planoRota;
    }

    @Transactional
    public String getReferencedWarning(final Long id) {
        final PlanoRota planoRota = planoRotaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!planoRota.getVisitasVisitas().isEmpty()) {
            return WebUtils.getMessage("planoRota.visita.oneToMany.referenced", planoRota.getVisitasVisitas().iterator().next().getId());
        }
        return null;
    }

}
