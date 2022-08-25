package br.jus.tse.distribuicao_urnas.service;

import br.jus.tse.distribuicao_urnas.domain.LocalVotacao;
import br.jus.tse.distribuicao_urnas.domain.Localizacao;
import br.jus.tse.distribuicao_urnas.domain.ZonaEleitoral;
import br.jus.tse.distribuicao_urnas.model.LocalVotacaoDTO;
import br.jus.tse.distribuicao_urnas.repos.LocalVotacaoRepository;
import br.jus.tse.distribuicao_urnas.repos.LocalizacaoRepository;
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
public class LocalVotacaoService {

    private final LocalVotacaoRepository localVotacaoRepository;
    private final ZonaEleitoralRepository zonaEleitoralRepository;
    private final LocalizacaoRepository localizacaoRepository;

    public LocalVotacaoService(final LocalVotacaoRepository localVotacaoRepository,
            final ZonaEleitoralRepository zonaEleitoralRepository,
            final LocalizacaoRepository localizacaoRepository) {
        this.localVotacaoRepository = localVotacaoRepository;
        this.zonaEleitoralRepository = zonaEleitoralRepository;
        this.localizacaoRepository = localizacaoRepository;
    }

    public List<LocalVotacaoDTO> findAll() {
        return localVotacaoRepository.findAll(Sort.by("id"))
                .stream()
                .map(localVotacao -> mapToDTO(localVotacao, new LocalVotacaoDTO()))
                .collect(Collectors.toList());
    }

    public LocalVotacaoDTO get(final Long id) {
        return localVotacaoRepository.findById(id)
                .map(localVotacao -> mapToDTO(localVotacao, new LocalVotacaoDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final LocalVotacaoDTO localVotacaoDTO) {
        final LocalVotacao localVotacao = new LocalVotacao();
        mapToEntity(localVotacaoDTO, localVotacao);
        return localVotacaoRepository.save(localVotacao).getId();
    }

    public void update(final Long id, final LocalVotacaoDTO localVotacaoDTO) {
        final LocalVotacao localVotacao = localVotacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(localVotacaoDTO, localVotacao);
        localVotacaoRepository.save(localVotacao);
    }

    public void delete(final Long id) {
        localVotacaoRepository.deleteById(id);
    }

    private LocalVotacaoDTO mapToDTO(final LocalVotacao localVotacao,
            final LocalVotacaoDTO localVotacaoDTO) {
        localVotacaoDTO.setId(localVotacao.getId());
        localVotacaoDTO.setNumero(localVotacao.getNumero());
        localVotacaoDTO.setNome(localVotacao.getNome());
        localVotacaoDTO.setEndereco(localVotacao.getEndereco());
        localVotacaoDTO.setQuantidadeSecoes(localVotacao.getQuantidadeSecoes());
        localVotacaoDTO.setZonaEleitoral(localVotacao.getZonaEleitoral() == null ? null : localVotacao.getZonaEleitoral().getId());
        localVotacaoDTO.setLocalizacao(localVotacao.getLocalizacao() == null ? null : localVotacao.getLocalizacao().getId());
        return localVotacaoDTO;
    }

    private LocalVotacao mapToEntity(final LocalVotacaoDTO localVotacaoDTO,
            final LocalVotacao localVotacao) {
        localVotacao.setNumero(localVotacaoDTO.getNumero());
        localVotacao.setNome(localVotacaoDTO.getNome());
        localVotacao.setEndereco(localVotacaoDTO.getEndereco());
        localVotacao.setQuantidadeSecoes(localVotacaoDTO.getQuantidadeSecoes());
        final ZonaEleitoral zonaEleitoral = localVotacaoDTO.getZonaEleitoral() == null ? null : zonaEleitoralRepository.findById(localVotacaoDTO.getZonaEleitoral())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "zonaEleitoral not found"));
        localVotacao.setZonaEleitoral(zonaEleitoral);
        final Localizacao localizacao = localVotacaoDTO.getLocalizacao() == null ? null : localizacaoRepository.findById(localVotacaoDTO.getLocalizacao())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "localizacao not found"));
        localVotacao.setLocalizacao(localizacao);
        return localVotacao;
    }

    @Transactional
    public String getReferencedWarning(final Long id) {
        final LocalVotacao localVotacao = localVotacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!localVotacao.getLocalVotacaoVisitas().isEmpty()) {
            return WebUtils.getMessage("localVotacao.visita.manyToOne.referenced", localVotacao.getLocalVotacaoVisitas().iterator().next().getId());
        }
        return null;
    }

}
