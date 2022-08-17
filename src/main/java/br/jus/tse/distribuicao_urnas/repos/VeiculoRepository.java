package br.jus.tse.distribuicao_urnas.repos;

import br.jus.tse.distribuicao_urnas.domain.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
}
