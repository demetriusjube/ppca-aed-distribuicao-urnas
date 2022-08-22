package br.jus.tse.distribuicao_urnas.solver.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.PrimitiveIterator;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.jus.tse.distribuicao_urnas.distance.DistanceCalculator;
import br.jus.tse.distribuicao_urnas.domain.CentroDistribuicao;
import br.jus.tse.distribuicao_urnas.domain.LocalVotacao;
import br.jus.tse.distribuicao_urnas.model.TipoOtimizacaoEnum;
import br.jus.tse.distribuicao_urnas.repos.CentroDistribuicaoRepository;
import br.jus.tse.distribuicao_urnas.repos.LocalVotacaoRepository;
import br.jus.tse.distribuicao_urnas.solver.domain.Customer;
import br.jus.tse.distribuicao_urnas.solver.domain.Depot;
import br.jus.tse.distribuicao_urnas.solver.domain.Location;
import br.jus.tse.distribuicao_urnas.solver.domain.Vehicle;
import br.jus.tse.distribuicao_urnas.solver.domain.VehicleRoutingSolution;

@Component
public class DistribuicaoUrnasSolutionBuilder {

	private static final AtomicLong sequence = new AtomicLong();

	@Autowired
	private CentroDistribuicaoRepository centroDistribuicaoRepository;

	@Autowired
	private LocalVotacaoRepository localVotacaoRepository;

	@Autowired
	private DistanceCalculator distanceCalculator;

	private DistribuicaoUrnasSolutionBuilder() {
	}

	public VehicleRoutingSolution build(Long idCentroDistribuicao, Integer numeroVeiculos, Integer capacidade,
			TipoOtimizacaoEnum tipoOtimizacao) {

		Location southWestCorner = new Location(0L, new BigDecimal(-16.04871827), new BigDecimal(-48.04740728));
		Location northEastCorner = new Location(0L, new BigDecimal(-15.7041622), new BigDecimal(-47.3737344));
		;

		Optional<CentroDistribuicao> consultaCentroDistribuicao = centroDistribuicaoRepository
				.findById(idCentroDistribuicao);
		if (consultaCentroDistribuicao.isPresent()) {
			CentroDistribuicao centroDistribuicao = consultaCentroDistribuicao.get();
			Depot depot = new Depot(centroDistribuicao.getId(), LocationBuilder.buildFrom(centroDistribuicao));

			List<Depot> depotList = Arrays.asList(depot);

			Supplier<Vehicle> vehicleSupplier = () -> new Vehicle(sequence.incrementAndGet(), capacidade, depot);

			List<Vehicle> vehicleList = Stream.generate(vehicleSupplier).limit(numeroVeiculos)
					.collect(Collectors.toList());

			List<LocalVotacao> locaisVotacaoDoCentro = localVotacaoRepository
					.findByZonaEleitoralCentroDistribuicaoEquals(centroDistribuicao);

			if (CollectionUtils.isEmpty(locaisVotacaoDoCentro)) {
				throw new IllegalArgumentException(
						"O centro de distribuição selecionado não tem locais de votação cadastrados!");
			}

			List<Customer> customers = new ArrayList<Customer>();
			for (LocalVotacao localVotacao : locaisVotacaoDoCentro) {
				Customer customer = new Customer(localVotacao.getId(), LocationBuilder.buildFrom(localVotacao),
						localVotacao.getQuantidadeSecoes());
				customers.add(customer);
			}

			List<Location> locationList = Stream
					.concat(customers.stream().map(Customer::getLocation), depotList.stream().map(Depot::getLocation))
					.collect(Collectors.toList());

			distanceCalculator.initDistanceMaps(locationList, tipoOtimizacao);

			return new VehicleRoutingSolution("Teste de otimização", locationList, depotList, vehicleList, customers,
					southWestCorner, northEastCorner);

		}
		throw new IllegalArgumentException("Não foi possível encontrar o centro de distribuição informado!");

	}
}
