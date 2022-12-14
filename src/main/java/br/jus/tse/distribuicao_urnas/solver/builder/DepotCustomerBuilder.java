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

	private static final long CONVERSOR_MINUTOS_MILIS = 60l * 1000l;
	private static final long CONVERSOR_HORA_MILIS = 60l * CONVERSOR_MINUTOS_MILIS;

	@Autowired
	private CentroDistribuicaoRepository centroDistribuicaoRepository;

	@Autowired
	private LocalVotacaoRepository localVotacaoRepository;

	public DepotCustomers build(Long idCentroDistribuicao, Integer tempoDescarregamentoMinutos,
			Integer tempoMaximoAtuacaoHoras) {

		Optional<CentroDistribuicao> consultaCentroDistribuicao = centroDistribuicaoRepository
				.findById(idCentroDistribuicao);
		if (consultaCentroDistribuicao.isPresent()) {
			CentroDistribuicao centroDistribuicao = consultaCentroDistribuicao.get();
			return build(centroDistribuicao, tempoDescarregamentoMinutos, tempoMaximoAtuacaoHoras);
		}
		throw new IllegalArgumentException("Não foi possível encontrar o centro de distribuição informado!");
	}

	public DepotCustomers build(CentroDistribuicao centroDistribuicao, Integer tempoDescarregamentoMinutos,
			Integer tempoMaximoAtuacaoHoras) {
		long tempoMaximoAtuacaoMilis = tempoMaximoAtuacaoHoras * CONVERSOR_HORA_MILIS;
		DepotCustomers depotCustomers = new DepotCustomers();
		Depot depot = new Depot(centroDistribuicao.getId(), LocationBuilder.buildFrom(centroDistribuicao),
				centroDistribuicao.getNome(), 0l, tempoMaximoAtuacaoMilis);

		List<LocalVotacao> locaisVotacaoDoCentro = localVotacaoRepository
				.findByZonaEleitoralCentroDistribuicaoEquals(centroDistribuicao);

		if (CollectionUtils.isEmpty(locaisVotacaoDoCentro)) {
			throw new IllegalArgumentException(
					"O centro de distribuição selecionado não tem locais de votação cadastrados!");
		}

		List<Customer> customers = getCustomersFromLocaisDeVotacao(locaisVotacaoDoCentro, tempoDescarregamentoMinutos);

		if (CollectionUtils.isEmpty(customers)) {
			throw new IllegalArgumentException("Não é possível fazer a simulação sem locais de votação!");
		}
		depotCustomers.setDepot(depot);
		depotCustomers.setCustomerList(customers);
		return depotCustomers;
	}

	public List<Customer> getCustomersFromLocaisDeVotacao(List<LocalVotacao> locaisVotacao,
			Integer tempoDescarregamentoMinutos) {
		List<Customer> customers = new ArrayList<Customer>();
		for (LocalVotacao localVotacao : locaisVotacao) {
			Customer customer = new Customer(localVotacao.getId(), localVotacao.getNome(),
					LocationBuilder.buildFrom(localVotacao), localVotacao.getQuantidadeSecoes(),
					tempoDescarregamentoMinutos);
			customers.add(customer);
		}
		return customers;
	}
}
