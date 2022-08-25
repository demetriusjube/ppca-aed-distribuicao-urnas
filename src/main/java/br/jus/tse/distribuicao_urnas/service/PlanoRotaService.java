package br.jus.tse.distribuicao_urnas.service;

import br.jus.tse.distribuicao_urnas.domain.PlanoRota;
import br.jus.tse.distribuicao_urnas.model.PlanoRotaDTO;
import br.jus.tse.distribuicao_urnas.repos.PlanoRotaRepository;
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

    public PlanoRotaService(final PlanoRotaRepository planoRotaRepository) {
        this.planoRotaRepository = planoRotaRepository;
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
        return planoRotaDTO;
    }

    private PlanoRota mapToEntity(final PlanoRotaDTO planoRotaDTO, final PlanoRota planoRota) {
        return planoRota;
    }

    @Transactional
    public String getReferencedWarning(final Long id) {
        final PlanoRota planoRota = planoRotaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!planoRota.getVisitas().isEmpty()) {
            return WebUtils.getMessage("planoRota.visita.oneToMany.referenced", planoRota.getVisitas().iterator().next().getId());
        } else if (!planoRota.getPlanoRotaVeiculoSimulacaos().isEmpty()) {
            return WebUtils.getMessage("planoRota.veiculoSimulacao.manyToOne.referenced", planoRota.getPlanoRotaVeiculoSimulacaos().iterator().next().getId());
        }
        return null;
    }

}
