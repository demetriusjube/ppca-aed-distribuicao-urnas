package br.jus.tse.distribuicao_urnas.repos;

import br.jus.tse.distribuicao_urnas.domain.Localizacao;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {

	Optional<Localizacao> findByLatitudeEqualsAndLongitudeEquals(BigDecimal latitude, BigDecimal longitude);
}
