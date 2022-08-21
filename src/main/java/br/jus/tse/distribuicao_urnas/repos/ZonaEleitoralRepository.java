package br.jus.tse.distribuicao_urnas.repos;

import br.jus.tse.distribuicao_urnas.domain.ZonaEleitoral;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ZonaEleitoralRepository extends JpaRepository<ZonaEleitoral, Long> {

	Optional<ZonaEleitoral> findByNumero(Integer numeroZE);

}
