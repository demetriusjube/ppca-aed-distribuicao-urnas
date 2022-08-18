package br.jus.tse.distribuicao_urnas.repos;

import br.jus.tse.distribuicao_urnas.domain.CentroDistribuicao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CentroDistribuicaoRepository extends JpaRepository<CentroDistribuicao, Long> {

	Optional<CentroDistribuicao> findByEnderecoEquals(String endereco);
}
