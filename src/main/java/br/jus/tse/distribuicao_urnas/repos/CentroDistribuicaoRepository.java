package br.jus.tse.distribuicao_urnas.repos;

import br.jus.tse.distribuicao_urnas.domain.CentroDistribuicao;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CentroDistribuicaoRepository extends JpaRepository<CentroDistribuicao, Long> {
}
