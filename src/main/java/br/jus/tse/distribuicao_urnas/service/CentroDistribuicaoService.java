package br.jus.tse.distribuicao_urnas.service;

import br.jus.tse.distribuicao_urnas.domain.CentroDistribuicao;
import br.jus.tse.distribuicao_urnas.domain.Localizacao;
import br.jus.tse.distribuicao_urnas.model.CentroDistribuicaoDTO;
import br.jus.tse.distribuicao_urnas.repos.CentroDistribuicaoRepository;
import br.jus.tse.distribuicao_urnas.repos.LocalizacaoRepository;
import br.jus.tse.distribuicao_urnas.util.WebUtils;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class CentroDistribuicaoService {

    private final CentroDistribuicaoRepository centroDistribuicaoRepository;
    private final LocalizacaoRepository localizacaoRepository;

    public CentroDistribuicaoService(
            final CentroDistribuicaoRepository centroDistribuicaoRepository,
            final LocalizacaoRepository localizacaoRepository) {
        this.centroDistribuicaoRepository = centroDistribuicaoRepository;
        this.localizacaoRepository = localizacaoRepository;
    }

    public List<CentroDistribuicaoDTO> findAll() {
        return centroDistribuicaoRepository.findAll(Sort.by("id"))
                .stream()
                .map(centroDistribuicao -> mapToDTO(centroDistribuicao, new CentroDistribuicaoDTO()))
                .collect(Collectors.toList());
    }

    public CentroDistribuicaoDTO get(final Long id) {
        return centroDistribuicaoRepository.findById(id)
                .map(centroDistribuicao -> mapToDTO(centroDistribuicao, new CentroDistribuicaoDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final CentroDistribuicaoDTO centroDistribuicaoDTO) {
        final CentroDistribuicao centroDistribuicao = new CentroDistribuicao();
        mapToEntity(centroDistribuicaoDTO, centroDistribuicao);
        return centroDistribuicaoRepository.save(centroDistribuicao).getId();
    }

    public void update(final Long id, final CentroDistribuicaoDTO centroDistribuicaoDTO) {
        final CentroDistribuicao centroDistribuicao = centroDistribuicaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(centroDistribuicaoDTO, centroDistribuicao);
        centroDistribuicaoRepository.save(centroDistribuicao);
    }

    public void delete(final Long id) {
        centroDistribuicaoRepository.deleteById(id);
    }

    private CentroDistribuicaoDTO mapToDTO(final CentroDistribuicao centroDistribuicao,
            final CentroDistribuicaoDTO centroDistribuicaoDTO) {
        centroDistribuicaoDTO.setId(centroDistribuicao.getId());
        centroDistribuicaoDTO.setNome(centroDistribuicao.getNome());
        centroDistribuicaoDTO.setEndereco(centroDistribuicao.getEndereco());
        centroDistribuicaoDTO.setLocalizacao(centroDistribuicao.getLocalizacao() == null ? null : centroDistribuicao.getLocalizacao().getId());
        return centroDistribuicaoDTO;
    }

    private CentroDistribuicao mapToEntity(final CentroDistribuicaoDTO centroDistribuicaoDTO,
            final CentroDistribuicao centroDistribuicao) {
        centroDistribuicao.setNome(centroDistribuicaoDTO.getNome());
        centroDistribuicao.setEndereco(centroDistribuicaoDTO.getEndereco());
        final Localizacao localizacao = centroDistribuicaoDTO.getLocalizacao() == null ? null : localizacaoRepository.findById(centroDistribuicaoDTO.getLocalizacao())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "localizacao not found"));
        centroDistribuicao.setLocalizacao(localizacao);
        return centroDistribuicao;
    }

    @Transactional
    public String getReferencedWarning(final Long id) {
        final CentroDistribuicao centroDistribuicao = centroDistribuicaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!centroDistribuicao.getCentroDistribuicaoZonaEleitorals().isEmpty()) {
            return WebUtils.getMessage("centroDistribuicao.zonaEleitoral.manyToOne.referenced", centroDistribuicao.getCentroDistribuicaoZonaEleitorals().iterator().next().getId());
        } else if (!centroDistribuicao.getCentroDistribuicaoSimulacaos().isEmpty()) {
            return WebUtils.getMessage("centroDistribuicao.simulacao.manyToOne.referenced", centroDistribuicao.getCentroDistribuicaoSimulacaos().iterator().next().getId());
        }
        return null;
    }

}
