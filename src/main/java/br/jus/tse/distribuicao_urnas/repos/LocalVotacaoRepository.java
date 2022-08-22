package br.jus.tse.distribuicao_urnas.repos;

import br.jus.tse.distribuicao_urnas.domain.CentroDistribuicao;
import br.jus.tse.distribuicao_urnas.domain.LocalVotacao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalVotacaoRepository extends JpaRepository<LocalVotacao, Long> {

	Optional<LocalVotacao> findByNumeroEqualsAndZonaEleitoralNumeroEquals(Integer numeroLocalVotacao, Integer numeroZE);

	List<LocalVotacao> findByZonaEleitoralCentroDistribuicaoEquals(CentroDistribuicao centroDistribuicao);

}
