package br.jus.tse.distribuicao_urnas.repos;

import br.jus.tse.distribuicao_urnas.domain.Simulacao;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SimulacaoRepository extends JpaRepository<Simulacao, Long> {
}
