package br.jus.tse.distribuicao_urnas.service;

import br.jus.tse.distribuicao_urnas.domain.Distancia;
import br.jus.tse.distribuicao_urnas.domain.Localizacao;
import br.jus.tse.distribuicao_urnas.model.DistanciaDTO;
import br.jus.tse.distribuicao_urnas.repos.DistanciaRepository;
import br.jus.tse.distribuicao_urnas.repos.LocalizacaoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class DistanciaService {

    private final DistanciaRepository distanciaRepository;
    private final LocalizacaoRepository localizacaoRepository;

    public DistanciaService(final DistanciaRepository distanciaRepository,
            final LocalizacaoRepository localizacaoRepository) {
        this.distanciaRepository = distanciaRepository;
        this.localizacaoRepository = localizacaoRepository;
    }

    public List<DistanciaDTO> findAll() {
        return distanciaRepository.findAll(Sort.by("id"))
                .stream()
                .map(distancia -> mapToDTO(distancia, new DistanciaDTO()))
                .collect(Collectors.toList());
    }

    public DistanciaDTO get(final Long id) {
        return distanciaRepository.findById(id)
                .map(distancia -> mapToDTO(distancia, new DistanciaDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final DistanciaDTO distanciaDTO) {
        final Distancia distancia = new Distancia();
        mapToEntity(distanciaDTO, distancia);
        return distanciaRepository.save(distancia).getId();
    }

    public void update(final Long id, final DistanciaDTO distanciaDTO) {
        final Distancia distancia = distanciaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(distanciaDTO, distancia);
        distanciaRepository.save(distancia);
    }

    public void delete(final Long id) {
        distanciaRepository.deleteById(id);
    }

    private DistanciaDTO mapToDTO(final Distancia distancia, final DistanciaDTO distanciaDTO) {
        distanciaDTO.setId(distancia.getId());
        distanciaDTO.setMenorTempoViagem(distancia.getMenorTempoViagem());
        distanciaDTO.setMenorDistancia(distancia.getMenorDistancia());
        distanciaDTO.setOrigem(distancia.getOrigem() == null ? null : distancia.getOrigem().getId());
        distanciaDTO.setDestino(distancia.getDestino() == null ? null : distancia.getDestino().getId());
        return distanciaDTO;
    }

    private Distancia mapToEntity(final DistanciaDTO distanciaDTO, final Distancia distancia) {
        distancia.setMenorTempoViagem(distanciaDTO.getMenorTempoViagem());
        distancia.setMenorDistancia(distanciaDTO.getMenorDistancia());
        final Localizacao origem = distanciaDTO.getOrigem() == null ? null : localizacaoRepository.findById(distanciaDTO.getOrigem())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "origem not found"));
        distancia.setOrigem(origem);
        final Localizacao destino = distanciaDTO.getDestino() == null ? null : localizacaoRepository.findById(distanciaDTO.getDestino())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "destino not found"));
        distancia.setDestino(destino);
        return distancia;
    }

}
