package br.jus.tse.distribuicao_urnas.service;

import br.jus.tse.distribuicao_urnas.domain.TRE;
import br.jus.tse.distribuicao_urnas.model.TREDTO;
import br.jus.tse.distribuicao_urnas.repos.TRERepository;
import br.jus.tse.distribuicao_urnas.util.WebUtils;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class TREService {

    private final TRERepository tRERepository;

    public TREService(final TRERepository tRERepository) {
        this.tRERepository = tRERepository;
    }

    public List<TREDTO> findAll() {
        return tRERepository.findAll(Sort.by("id"))
                .stream()
                .map(tRE -> mapToDTO(tRE, new TREDTO()))
                .collect(Collectors.toList());
    }

    public TREDTO get(final Long id) {
        return tRERepository.findById(id)
                .map(tRE -> mapToDTO(tRE, new TREDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final TREDTO tREDTO) {
        final TRE tRE = new TRE();
        mapToEntity(tREDTO, tRE);
        return tRERepository.save(tRE).getId();
    }

    public void update(final Long id, final TREDTO tREDTO) {
        final TRE tRE = tRERepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(tREDTO, tRE);
        tRERepository.save(tRE);
    }

    public void delete(final Long id) {
        tRERepository.deleteById(id);
    }

    private TREDTO mapToDTO(final TRE tRE, final TREDTO tREDTO) {
        tREDTO.setId(tRE.getId());
        tREDTO.setNumero(tRE.getNumero());
        tREDTO.setUf(tRE.getUf());
        return tREDTO;
    }

    private TRE mapToEntity(final TREDTO tREDTO, final TRE tRE) {
        tRE.setNumero(tREDTO.getNumero());
        tRE.setUf(tREDTO.getUf());
        return tRE;
    }

    @Transactional
    public String getReferencedWarning(final Long id) {
        final TRE tRE = tRERepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!tRE.getTreZonaEleitorals().isEmpty()) {
            return WebUtils.getMessage("tRE.zonaEleitoral.manyToOne.referenced", tRE.getTreZonaEleitorals().iterator().next().getId());
        }
        return null;
    }

}
