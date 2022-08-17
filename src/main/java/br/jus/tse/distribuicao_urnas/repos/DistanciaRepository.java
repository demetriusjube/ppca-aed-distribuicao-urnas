package br.jus.tse.distribuicao_urnas.repos;

import br.jus.tse.distribuicao_urnas.domain.Distancia;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DistanciaRepository extends JpaRepository<Distancia, Long> {
}
