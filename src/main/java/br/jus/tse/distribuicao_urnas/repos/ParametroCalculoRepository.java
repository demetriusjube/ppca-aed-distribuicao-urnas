package br.jus.tse.distribuicao_urnas.repos;

import br.jus.tse.distribuicao_urnas.domain.ParametroCalculo;
import br.jus.tse.distribuicao_urnas.model.TipoParametroEnum;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ParametroCalculoRepository extends JpaRepository<ParametroCalculo, Long> {

	Optional<ParametroCalculo> findByTipoParametroEquals(TipoParametroEnum tipoParametro);
}
