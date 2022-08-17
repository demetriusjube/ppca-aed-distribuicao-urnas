package br.jus.tse.distribuicao_urnas.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.jus.tse.distribuicao_urnas.domain.ZonaEleitoral;

public interface ZonaEleitoralRepository extends JpaRepository<ZonaEleitoral, Long> {

	Optional<ZonaEleitoral> findByNumero(Long numeroZE);
}
