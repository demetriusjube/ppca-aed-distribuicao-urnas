package br.jus.tse.distribuicao_urnas.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import br.jus.tse.distribuicao_urnas.domain.Distancia;
import br.jus.tse.distribuicao_urnas.domain.Localizacao;

public interface DistanciaRepository extends JpaRepository<Distancia, Long> {

	boolean existsByOrigemEqualsAndDestinoEquals(Localizacao origem, Localizacao destino);
}
