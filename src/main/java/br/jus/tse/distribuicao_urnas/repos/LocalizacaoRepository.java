package br.jus.tse.distribuicao_urnas.repos;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.jus.tse.distribuicao_urnas.domain.Localizacao;

public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {

	Optional<Localizacao> findByLatitudeEqualsAndLongitudeEquals(BigDecimal latitude, BigDecimal longitude);
}
