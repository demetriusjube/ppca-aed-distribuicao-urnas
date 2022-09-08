package br.jus.tse.distribuicao_urnas.solver.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
import br.jus.tse.distribuicao_urnas.domain.Localizacao;
import br.jus.tse.distribuicao_urnas.domain.ParametroCalculo;
import br.jus.tse.distribuicao_urnas.domain.Simulacao;
import br.jus.tse.distribuicao_urnas.domain.Veiculo;
import br.jus.tse.distribuicao_urnas.domain.VeiculoSimulacao;
import br.jus.tse.distribuicao_urnas.domain.Visita;
import br.jus.tse.distribuicao_urnas.model.TipoOtimizacaoEnum;
import br.jus.tse.distribuicao_urnas.model.TipoParametroEnum;
import br.jus.tse.distribuicao_urnas.repos.CentroDistribuicaoRepository;
import br.jus.tse.distribuicao_urnas.repos.LocalVotacaoRepository;
import br.jus.tse.distribuicao_urnas.repos.ParametroCalculoRepository;
import br.jus.tse.distribuicao_urnas.repos.SimulacaoRepository;
import br.jus.tse.distribuicao_urnas.solver.domain.Customer;
import br.jus.tse.distribuicao_urnas.solver.domain.Depot;
import br.jus.tse.distribuicao_urnas.solver.domain.DepotCustomers;
import br.jus.tse.distribuicao_urnas.solver.domain.Location;
import br.jus.tse.distribuicao_urnas.solver.domain.SimulacaoRequest;
import br.jus.tse.distribuicao_urnas.solver.domain.Vehicle;
import br.jus.tse.distribuicao_urnas.solver.domain.VehicleRequest;
import br.jus.tse.distribuicao_urnas.solver.domain.VehicleRoutingSolution;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DistribuicaoUrnasSolutionBuilder {

	private static final AtomicLong sequence = new AtomicLong();

	@Autowired
	private DistanceCalculator distanceCalculator;

	@Autowired
	private DepotCustomerBuilder depotCustomerBuilder;

	@Autowired
	private SimulacaoRepository simulacaoRepository;

	private DistribuicaoUrnasSolutionBuilder() {
	}

	public VehicleRoutingSolution build(SimulacaoRequest simulacaoRequest) {
		Long idCentroDistribuicao = simulacaoRequest.getIdCentroDistribuicao();
		TipoOtimizacaoEnum tipoOtimizacaoEnum = simulacaoRequest.getTipoOtimizacaoEnum();
		DepotCustomers depotCustomers = depotCustomerBuilder.build(idCentroDistribuicao);
		List<Vehicle> vehicleList = montaVeiculosDaSimulacao(simulacaoRequest, depotCustomers.getDepot());
		return buildSolution(depotCustomers, vehicleList, tipoOtimizacaoEnum);

	}

	private VehicleRoutingSolution buildSolution(DepotCustomers depotCustomers, List<Vehicle> vehicleList,
			TipoOtimizacaoEnum tipoOtimizacaoEnum) {
		Location southWestCorner = new Location(0L, new BigDecimal(-16.04871827), new BigDecimal(-48.04740728), "", "");
		Location northEastCorner = new Location(0L, new BigDecimal(-15.7041622), new BigDecimal(-47.3737344), "", "");
		List<Depot> depotList = Arrays.asList(depotCustomers.getDepot());
		if (CollectionUtils.isEmpty(vehicleList)) {
			throw new IllegalArgumentException("Não é possível fazer a simulação com uma quantidade nula de veículos!");
		}
		List<Customer> customers = depotCustomers.getCustomerList();
		List<Location> locationList = Stream
				.concat(customers.stream().map(Customer::getLocation), depotList.stream().map(Depot::getLocation))
				.collect(Collectors.toList());
		log.info("Carregando a matriz de distâncias...");
		distanceCalculator.initDistanceMaps(locationList, tipoOtimizacaoEnum);
		log.info("Matriz de distâncias carregada!");
		return new VehicleRoutingSolution("Teste de otimização", locationList, depotList, vehicleList, customers,
				southWestCorner, northEastCorner);
	}

	private List<Vehicle> montaVeiculosDaSimulacao(SimulacaoRequest simulacaoRequest, Depot depot) {
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		if (CollectionUtils.isNotEmpty(simulacaoRequest.getVeiculos())) {
			for (VehicleRequest vehicleRequest : simulacaoRequest.getVeiculos()) {
				adicionaVeiculo(vehicleRequest, depot, vehicleList, simulacaoRequest.getTempoDescarregamentoMinutos(),
						simulacaoRequest.getTempoMaximoAtuacaoHoras());
			}
		}
		return vehicleList;
	}

	private void adicionaVeiculo(VehicleRequest vehicleRequest, Depot depot, List<Vehicle> vehicleList,
			Integer tempoDescarregamentoMinutos, Integer tempoMaximoAtuacao) {
		if (vehicleRequest != null) {
			Integer capacidade = vehicleRequest.getCapacidade();
			Supplier<Vehicle> vehicleSupplier = () -> new Vehicle(sequence.incrementAndGet(), capacidade.intValue(),
					depot, tempoDescarregamentoMinutos, tempoMaximoAtuacao);
			vehicleList.addAll(Stream.generate(vehicleSupplier).limit(vehicleRequest.getQuantidadeVeiculos())
					.collect(Collectors.toList()));
		}
	}

	public VehicleRoutingSolution build(Long idSimulacao) {
		Location southWestCorner = new Location(0L, new BigDecimal(-16.04871827), new BigDecimal(-48.04740728), "", "");
		Location northEastCorner = new Location(0L, new BigDecimal(-15.7041622), new BigDecimal(-47.3737344), "", "");
		Simulacao simulacao = simulacaoRepository.findById(idSimulacao).orElse(null);
		if (simulacao != null) {

			TipoOtimizacaoEnum tipoOtimizacaoEnum = TipoOtimizacaoEnum.MENOR_DISTANCIA;
			DepotCustomers depotCustomers = depotCustomerBuilder.build(simulacao.getCentroDistribuicao());
			List<Depot> depotList = Arrays.asList(depotCustomers.getDepot());
			List<Customer> customers = depotCustomers.getCustomerList();
			List<Location> locationList = Stream
					.concat(customers.stream().map(Customer::getLocation), depotList.stream().map(Depot::getLocation))
					.collect(Collectors.toList());
			log.info("Carregando a matriz de distâncias...");
			distanceCalculator.initDistanceMaps(locationList, tipoOtimizacaoEnum);
			log.info("Matriz de distâncias carregada!");
			List<Vehicle> vehicleList = montaVeiculosDaSimulacao(simulacao, depotCustomers.getDepot(), customers);
			return new VehicleRoutingSolution("Teste de otimização", locationList, depotList, vehicleList, customers,
					southWestCorner, northEastCorner);

		}
		throw new IllegalArgumentException("A simulação informada não foi encontrada!");
	}

	private List<Vehicle> montaVeiculosDaSimulacao(Simulacao simulacao, Depot depot,
			List<Customer> customersWithDistanceMatrix) {
		List<Vehicle> veiculos = new ArrayList<Vehicle>();
		for (VeiculoSimulacao veiculoSimulacao : simulacao.getSimulacaoVeiculoSimulacaos()) {
			Vehicle vehicle = new Vehicle();
			Veiculo veiculo = veiculoSimulacao.getVeiculo();
			vehicle.setDescription(veiculo.getDescricao());
			vehicle.setCapacity(veiculo.getCapacidade());
			vehicle.setDepot(depot);
			vehicle.setId(veiculo.getId());
			vehicle.setTempoDescarregamentoMinutos(30);
			vehicle.setTempoMaximoAtuacaoHoras(10);
			List<Visita> visitasOrdenadas = veiculoSimulacao.getPlanoRota().getVisitas().stream()
					.sorted(Comparator.comparing(Visita::getOrdem)).toList();
			List<Long> idsLocalizacoes = visitasOrdenadas.stream().map(Visita::getLocalVotacao)
					.map(LocalVotacao::getLocalizacao).map(Localizacao::getId).toList();
			List<Customer> customers = customersWithDistanceMatrix.stream()
					.filter(customer -> idsLocalizacoes.contains(customer.getLocation().getId())).toList();
			vehicle.setCustomerList(customers);
			veiculos.add(vehicle);
		}
		return veiculos;
	}

}
