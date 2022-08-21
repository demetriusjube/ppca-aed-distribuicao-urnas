package br.jus.tse.distribuicao_urnas.service;

import br.jus.tse.distribuicao_urnas.domain.CentroDistribuicao;
import br.jus.tse.distribuicao_urnas.domain.TRE;
import br.jus.tse.distribuicao_urnas.domain.ZonaEleitoral;
import br.jus.tse.distribuicao_urnas.model.ZonaEleitoralDTO;
import br.jus.tse.distribuicao_urnas.repos.CentroDistribuicaoRepository;
import br.jus.tse.distribuicao_urnas.repos.TRERepository;
import br.jus.tse.distribuicao_urnas.repos.ZonaEleitoralRepository;
import br.jus.tse.distribuicao_urnas.util.WebUtils;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ZonaEleitoralService {

    private final ZonaEleitoralRepository zonaEleitoralRepository;
    private final TRERepository tRERepository;
    private final CentroDistribuicaoRepository centroDistribuicaoRepository;

    public ZonaEleitoralService(final ZonaEleitoralRepository zonaEleitoralRepository,
            final TRERepository tRERepository,
            final CentroDistribuicaoRepository centroDistribuicaoRepository) {
        this.zonaEleitoralRepository = zonaEleitoralRepository;
        this.tRERepository = tRERepository;
        this.centroDistribuicaoRepository = centroDistribuicaoRepository;
    }

    public List<ZonaEleitoralDTO> findAll() {
        return zonaEleitoralRepository.findAll(Sort.by("id"))
                .stream()
                .map(zonaEleitoral -> mapToDTO(zonaEleitoral, new ZonaEleitoralDTO()))
                .collect(Collectors.toList());
    }

    public ZonaEleitoralDTO get(final Long id) {
        return zonaEleitoralRepository.findById(id)
                .map(zonaEleitoral -> mapToDTO(zonaEleitoral, new ZonaEleitoralDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final ZonaEleitoralDTO zonaEleitoralDTO) {
        final ZonaEleitoral zonaEleitoral = new ZonaEleitoral();
        mapToEntity(zonaEleitoralDTO, zonaEleitoral);
        return zonaEleitoralRepository.save(zonaEleitoral).getId();
    }

    public void update(final Long id, final ZonaEleitoralDTO zonaEleitoralDTO) {
        final ZonaEleitoral zonaEleitoral = zonaEleitoralRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(zonaEleitoralDTO, zonaEleitoral);
        zonaEleitoralRepository.save(zonaEleitoral);
    }

    public void delete(final Long id) {
        zonaEleitoralRepository.deleteById(id);
    }

    private ZonaEleitoralDTO mapToDTO(final ZonaEleitoral zonaEleitoral,
            final ZonaEleitoralDTO zonaEleitoralDTO) {
        zonaEleitoralDTO.setId(zonaEleitoral.getId());
        zonaEleitoralDTO.setNumero(zonaEleitoral.getNumero());
        zonaEleitoralDTO.setNome(zonaEleitoral.getNome());
        zonaEleitoralDTO.setTre(zonaEleitoral.getTre() == null ? null : zonaEleitoral.getTre().getId());
        zonaEleitoralDTO.setCentroDistribuicao(zonaEleitoral.getCentroDistribuicao() == null ? null : zonaEleitoral.getCentroDistribuicao().getId());
        return zonaEleitoralDTO;
    }

    private ZonaEleitoral mapToEntity(final ZonaEleitoralDTO zonaEleitoralDTO,
            final ZonaEleitoral zonaEleitoral) {
        zonaEleitoral.setNumero(zonaEleitoralDTO.getNumero());
        zonaEleitoral.setNome(zonaEleitoralDTO.getNome());
        final TRE tre = zonaEleitoralDTO.getTre() == null ? null : tRERepository.findById(zonaEleitoralDTO.getTre())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "tre not found"));
        zonaEleitoral.setTre(tre);
        final CentroDistribuicao centroDistribuicao = zonaEleitoralDTO.getCentroDistribuicao() == null ? null : centroDistribuicaoRepository.findById(zonaEleitoralDTO.getCentroDistribuicao())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "centroDistribuicao not found"));
        zonaEleitoral.setCentroDistribuicao(centroDistribuicao);
        return zonaEleitoral;
    }

    @Transactional
    public String getReferencedWarning(final Long id) {
        final ZonaEleitoral zonaEleitoral = zonaEleitoralRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!zonaEleitoral.getZonaEleitoralLocalVotacaos().isEmpty()) {
            return WebUtils.getMessage("zonaEleitoral.localVotacao.manyToOne.referenced", zonaEleitoral.getZonaEleitoralLocalVotacaos().iterator().next().getId());
        }
        return null;
    }

}
