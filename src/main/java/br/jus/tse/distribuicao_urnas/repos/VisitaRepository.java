package br.jus.tse.distribuicao_urnas.repos;

import br.jus.tse.distribuicao_urnas.domain.Visita;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VisitaRepository extends JpaRepository<Visita, Long> {
}
