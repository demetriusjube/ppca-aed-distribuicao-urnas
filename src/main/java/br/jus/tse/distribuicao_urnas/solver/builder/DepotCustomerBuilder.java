package br.jus.tse.distribuicao_urnas.solver.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.jus.tse.distribuicao_urnas.domain.CentroDistribuicao;
import br.jus.tse.distribuicao_urnas.domain.LocalVotacao;
import br.jus.tse.distribuicao_urnas.repos.CentroDistribuicaoRepository;
import br.jus.tse.distribuicao_urnas.repos.LocalVotacaoRepository;
import br.jus.tse.distribuicao_urnas.solver.domain.Customer;
import br.jus.tse.distribuicao_urnas.solver.domain.Depot;
import br.jus.tse.distribuicao_urnas.solver.domain.DepotCustomers;

@Component
public class DepotCustomerBuilder {

	@Autowired
	private CentroDistribuicaoRepository centroDistribuicaoRepository;

	@Autowired
	private LocalVotacaoRepository localVotacaoRepository;

	public DepotCustomers build(Long idCentroDistribuicao) {

		Optional<CentroDistribuicao> consultaCentroDistribuicao = centroDistribuicaoRepository
				.findById(idCentroDistribuicao);
		if (consultaCentroDistribuicao.isPresent()) {
			DepotCustomers depotCustomers = new DepotCustomers();
			CentroDistribuicao centroDistribuicao = consultaCentroDistribuicao.get();
			Depot depot = new Depot(centroDistribuicao.getId(), LocationBuilder.buildFrom(centroDistribuicao),
					centroDistribuicao.getNome());

			List<LocalVotacao> locaisVotacaoDoCentro = localVotacaoRepository
					.findByZonaEleitoralCentroDistribuicaoEquals(centroDistribuicao);

			if (CollectionUtils.isEmpty(locaisVotacaoDoCentro)) {
				throw new IllegalArgumentException(
						"O centro de distribuição selecionado não tem locais de votação cadastrados!");
			}

			List<Customer> customers = new ArrayList<Customer>();
			for (LocalVotacao localVotacao : locaisVotacaoDoCentro) {
				Customer customer = new Customer(localVotacao.getId(), localVotacao.getNome(),
						LocationBuilder.buildFrom(localVotacao), localVotacao.getQuantidadeSecoes());
				customers.add(customer);
			}

			if (CollectionUtils.isEmpty(customers)) {
				throw new IllegalArgumentException("Não é possível fazer a simulação sem locais de votação!");
			}
			depotCustomers.setDepot(depot);
			depotCustomers.setCustomerList(customers);
			return depotCustomers;
		}
		throw new IllegalArgumentException("Não foi possível encontrar o centro de distribuição informado!");
	}
}
