package br.jus.tse.distribuicao_urnas.repos;

import br.jus.tse.distribuicao_urnas.domain.RestricaoSimulacao;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RestricaoSimulacaoRepository extends JpaRepository<RestricaoSimulacao, Long> {
}
