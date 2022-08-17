package br.jus.tse.distribuicao_urnas.service;

import br.jus.tse.distribuicao_urnas.domain.ParametroCalculo;
import br.jus.tse.distribuicao_urnas.model.ParametroCalculoDTO;
import br.jus.tse.distribuicao_urnas.repos.ParametroCalculoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ParametroCalculoService {

    private final ParametroCalculoRepository parametroCalculoRepository;

    public ParametroCalculoService(final ParametroCalculoRepository parametroCalculoRepository) {
        this.parametroCalculoRepository = parametroCalculoRepository;
    }

    public List<ParametroCalculoDTO> findAll() {
        return parametroCalculoRepository.findAll(Sort.by("id"))
                .stream()
                .map(parametroCalculo -> mapToDTO(parametroCalculo, new ParametroCalculoDTO()))
                .collect(Collectors.toList());
    }

    public ParametroCalculoDTO get(final Long id) {
        return parametroCalculoRepository.findById(id)
                .map(parametroCalculo -> mapToDTO(parametroCalculo, new ParametroCalculoDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final ParametroCalculoDTO parametroCalculoDTO) {
        final ParametroCalculo parametroCalculo = new ParametroCalculo();
        mapToEntity(parametroCalculoDTO, parametroCalculo);
        return parametroCalculoRepository.save(parametroCalculo).getId();
    }

    public void update(final Long id, final ParametroCalculoDTO parametroCalculoDTO) {
        final ParametroCalculo parametroCalculo = parametroCalculoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(parametroCalculoDTO, parametroCalculo);
        parametroCalculoRepository.save(parametroCalculo);
    }

    public void delete(final Long id) {
        parametroCalculoRepository.deleteById(id);
    }

    private ParametroCalculoDTO mapToDTO(final ParametroCalculo parametroCalculo,
            final ParametroCalculoDTO parametroCalculoDTO) {
        parametroCalculoDTO.setId(parametroCalculo.getId());
        parametroCalculoDTO.setValor(parametroCalculo.getValor());
        parametroCalculoDTO.setTipoParametro(parametroCalculo.getTipoParametro());
        return parametroCalculoDTO;
    }

    private ParametroCalculo mapToEntity(final ParametroCalculoDTO parametroCalculoDTO,
            final ParametroCalculo parametroCalculo) {
        parametroCalculo.setValor(parametroCalculoDTO.getValor());
        parametroCalculo.setTipoParametro(parametroCalculoDTO.getTipoParametro());
        return parametroCalculo;
    }

}
