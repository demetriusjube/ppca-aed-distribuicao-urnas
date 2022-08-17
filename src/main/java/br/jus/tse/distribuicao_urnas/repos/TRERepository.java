package br.jus.tse.distribuicao_urnas.repos;

import br.jus.tse.distribuicao_urnas.domain.TRE;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TRERepository extends JpaRepository<TRE, Long> {

	Optional<TRE> findByUf(String uf);
}
