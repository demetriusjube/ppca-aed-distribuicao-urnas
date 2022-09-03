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
import br.jus.tse.distribuicao_urnas.solver.domain.VehicleRoutingSolution;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DistribuicaoUrnasSolutionBuilder {

	private static final float VOLUME_38M3 = 38f;
	private static final float VOLUME_22M3 = 22f;
	private static final float VOLUME_13M3 = 13f;
	private static final float VOLUME_7_5M3 = 7.5f;

	private static final double VALOR_MAIOR_VOLUME_URNA = 0.52031d;

	private static final AtomicLong sequence = new AtomicLong();

	@Autowired
	private ParametroCalculoRepository parametroCalculoRepository;

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
		return new VehicleRoutingSolution("Teste de otimização", depotList, vehicleList, customers);
	}

	private List<Vehicle> montaVeiculosDaSimulacao(SimulacaoRequest simulacaoRequest, Depot depot) {
		Optional<ParametroCalculo> parametroVolumeUrna = parametroCalculoRepository
				.findByTipoParametroEquals(TipoParametroEnum.VOLUME_URNA_APOS_2020);
		Double volumeUrna = VALOR_MAIOR_VOLUME_URNA;
		if (parametroVolumeUrna.isPresent()) {
			volumeUrna = parametroVolumeUrna.get().getValor();
		}
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		adicionaVeiculos38m3(simulacaoRequest, volumeUrna, depot, vehicleList);
		adicionaVeiculos22m3(simulacaoRequest, volumeUrna, depot, vehicleList);
		adicionaVeiculos13m3(simulacaoRequest, volumeUrna, depot, vehicleList);
		adicionaVeiculos7_5m3(simulacaoRequest, volumeUrna, depot, vehicleList);
		return vehicleList;
	}

	private void adicionaVeiculos38m3(SimulacaoRequest simulacaoRequest, Double volumeUrna, Depot depot,
			List<Vehicle> vehicleList) {
		adicionaVeiculo(simulacaoRequest.getQuantidadeCaminhoes38m3(), volumeUrna, depot, vehicleList, VOLUME_38M3,
				simulacaoRequest.getTempoDescarregamentoMinutos(), simulacaoRequest.getTempoMaximoAtuacaoHoras());
	}

	private void adicionaVeiculo(Integer quantidadeVeiculos, Double volumeUrna, Depot depot, List<Vehicle> vehicleList,
			float volumeVeiculo, Integer tempoDescarregamentoMinutos, Integer tempoMaximoAtuacao) {
		if (quantidadeVeiculos != null) {
			Double capacidade = Math.floor(volumeVeiculo / volumeUrna);
			Supplier<Vehicle> vehicleSupplier = () -> new Vehicle(sequence.incrementAndGet(), capacidade.intValue(),
					depot, tempoDescarregamentoMinutos, tempoMaximoAtuacao);
			vehicleList.addAll(Stream.generate(vehicleSupplier).limit(quantidadeVeiculos).collect(Collectors.toList()));
		}
	}

	private void adicionaVeiculos22m3(SimulacaoRequest simulacaoRequest, Double volumeUrna, Depot depot,
			List<Vehicle> vehicleList) {
		adicionaVeiculo(simulacaoRequest.getQuantidadeCaminhoes22m3(), volumeUrna, depot, vehicleList, VOLUME_22M3,
				simulacaoRequest.getTempoDescarregamentoMinutos(), simulacaoRequest.getTempoMaximoAtuacaoHoras());
	}

	private void adicionaVeiculos13m3(SimulacaoRequest simulacaoRequest, Double volumeUrna, Depot depot,
			List<Vehicle> vehicleList) {
		adicionaVeiculo(simulacaoRequest.getQuantidadeCaminhoes13m3(), volumeUrna, depot, vehicleList, VOLUME_13M3,
				simulacaoRequest.getTempoDescarregamentoMinutos(), simulacaoRequest.getTempoMaximoAtuacaoHoras());
	}

	private void adicionaVeiculos7_5m3(SimulacaoRequest simulacaoRequest, Double volumeUrna, Depot depot,
			List<Vehicle> vehicleList) {
		adicionaVeiculo(simulacaoRequest.getQuantidadeCaminhoes7_5m3(), volumeUrna, depot, vehicleList, VOLUME_7_5M3,
				simulacaoRequest.getTempoDescarregamentoMinutos(), simulacaoRequest.getTempoMaximoAtuacaoHoras());
	}

	public VehicleRoutingSolution build(Long idSimulacao) {
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
			return new VehicleRoutingSolution("Teste de otimização", depotList, vehicleList, customers);

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
			Customer primeiraVisita = mountCustomersVisits(visitasOrdenadas, customersWithDistanceMatrix, vehicle);
//			List<Long> idsLocalizacoes = visitasOrdenadas.stream().map(Visita::getLocalVotacao)
//					.map(LocalVotacao::getLocalizacao).map(Localizacao::getId).toList();
//			List<Customer> customers = customersWithDistanceMatrix.stream()
//					.filter(customer -> idsLocalizacoes.contains(customer.getLocation().getId())).toList();
			vehicle.setNextVisit(primeiraVisita);
			veiculos.add(vehicle);
		}
		return veiculos;
	}

	private Customer mountCustomersVisits(List<Visita> visitasOrdenadas, List<Customer> customersWithDistanceMatrix,
			Vehicle vehicle) {
		Customer primeiraVisita = null;
		Customer visitaAnterior = null;
		for (Visita visita : visitasOrdenadas) {
			Customer visitaCorrente = getCustomerFromVisita(customersWithDistanceMatrix, visita);
			visitaCorrente.setVehicle(vehicle);
			if (primeiraVisita == null) {
				visitaCorrente.setPreviousStandstill(vehicle);
				primeiraVisita = visitaCorrente;
			} else {
				visitaCorrente.setPreviousStandstill(visitaAnterior);
				visitaAnterior.setNextVisit(visitaCorrente);
			}
			visitaAnterior = visitaCorrente;
		}
		return primeiraVisita;
	}

	public Customer getCustomerFromVisita(List<Customer> customersWithDistanceMatrix, Visita visita) {
		return customersWithDistanceMatrix.stream().filter(
				customer -> visita.getLocalVotacao().getLocalizacao().getId().equals(customer.getLocation().getId()))
				.findFirst().get();
	}

}
