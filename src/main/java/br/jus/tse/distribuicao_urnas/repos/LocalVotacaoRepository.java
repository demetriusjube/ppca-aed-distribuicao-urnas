package br.jus.tse.distribuicao_urnas.repos;

import br.jus.tse.distribuicao_urnas.domain.LocalVotacao;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LocalVotacaoRepository extends JpaRepository<LocalVotacao, Long> {
}
