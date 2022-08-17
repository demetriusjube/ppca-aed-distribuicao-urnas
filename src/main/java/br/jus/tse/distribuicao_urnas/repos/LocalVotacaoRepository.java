package br.jus.tse.distribuicao_urnas.repos;

import br.jus.tse.distribuicao_urnas.domain.LocalVotacao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalVotacaoRepository extends JpaRepository<LocalVotacao, Long> {

	Optional<LocalVotacao> findByNumero(Integer numeroLocalVotacao);
}
