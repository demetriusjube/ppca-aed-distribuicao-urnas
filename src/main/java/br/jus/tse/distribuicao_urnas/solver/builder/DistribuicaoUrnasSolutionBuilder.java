package br.jus.tse.distribuicao_urnas.solver.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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
import br.jus.tse.distribuicao_urnas.domain.ParametroCalculo;
import br.jus.tse.distribuicao_urnas.model.TipoOtimizacaoEnum;
import br.jus.tse.distribuicao_urnas.model.TipoParametroEnum;
import br.jus.tse.distribuicao_urnas.repos.CentroDistribuicaoRepository;
import br.jus.tse.distribuicao_urnas.repos.LocalVotacaoRepository;
import br.jus.tse.distribuicao_urnas.repos.ParametroCalculoRepository;
import br.jus.tse.distribuicao_urnas.solver.domain.Customer;
import br.jus.tse.distribuicao_urnas.solver.domain.Depot;
import br.jus.tse.distribuicao_urnas.solver.domain.Location;
import br.jus.tse.distribuicao_urnas.solver.domain.SimulacaoRequest;
import br.jus.tse.distribuicao_urnas.solver.domain.Vehicle;
import br.jus.tse.distribuicao_urnas.solver.domain.VehicleRoutingSolution;

@Component
public class DistribuicaoUrnasSolutionBuilder {

	private static final float VOLUME_38M3 = 38f;
	private static final float VOLUME_22M3 = 22f;
	private static final float VOLUME_13M3 = 13f;
	private static final float VOLUME_7_5M3 = 7.5f;

	private static final double VALOR_MAIOR_VOLUME_URNA = 0.52031d;

	private static final AtomicLong sequence = new AtomicLong();

	@Autowired
	private CentroDistribuicaoRepository centroDistribuicaoRepository;

	@Autowired
	private LocalVotacaoRepository localVotacaoRepository;

	@Autowired
	private ParametroCalculoRepository parametroCalculoRepository;

	@Autowired
	private DistanceCalculator distanceCalculator;

	private DistribuicaoUrnasSolutionBuilder() {
	}

	public VehicleRoutingSolution build(SimulacaoRequest simulacaoRequest) {

		Location southWestCorner = new Location(0L, new BigDecimal(-16.04871827), new BigDecimal(-48.04740728), "");
		Location northEastCorner = new Location(0L, new BigDecimal(-15.7041622), new BigDecimal(-47.3737344), "");
		;

		Optional<CentroDistribuicao> consultaCentroDistribuicao = centroDistribuicaoRepository
				.findById(simulacaoRequest.getIdCentroDistribuicao());
		if (consultaCentroDistribuicao.isPresent()) {
			Optional<ParametroCalculo> parametroVolumeUrna = parametroCalculoRepository
					.findByTipoParametroEquals(TipoParametroEnum.VOLUME_URNA_APOS_2020);
			Double volumeUrna = VALOR_MAIOR_VOLUME_URNA;
			if (parametroVolumeUrna.isPresent()) {
				volumeUrna = parametroVolumeUrna.get().getValor();
			}
			CentroDistribuicao centroDistribuicao = consultaCentroDistribuicao.get();
			Depot depot = new Depot(centroDistribuicao.getId(), LocationBuilder.buildFrom(centroDistribuicao),
					centroDistribuicao.getNome());

			List<Depot> depotList = Arrays.asList(depot);

			List<Vehicle> vehicleList = new ArrayList<Vehicle>();
			adicionaVeiculos38m3(simulacaoRequest, volumeUrna, depot, vehicleList);
			adicionaVeiculos22m3(simulacaoRequest, volumeUrna, depot, vehicleList);
			adicionaVeiculos13m3(simulacaoRequest, volumeUrna, depot, vehicleList);
			adicionaVeiculos7_5m3(simulacaoRequest, volumeUrna, depot, vehicleList);

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

			List<Location> locationList = Stream
					.concat(customers.stream().map(Customer::getLocation), depotList.stream().map(Depot::getLocation))
					.collect(Collectors.toList());

			distanceCalculator.initDistanceMaps(locationList, simulacaoRequest.getTipoOtimizacaoEnum());

			return new VehicleRoutingSolution("Teste de otimização", locationList, depotList, vehicleList, customers,
					southWestCorner, northEastCorner);

		}
		throw new IllegalArgumentException("Não foi possível encontrar o centro de distribuição informado!");

	}

	private void adicionaVeiculos38m3(SimulacaoRequest simulacaoRequest, Double volumeUrna, Depot depot,
			List<Vehicle> vehicleList) {
		if (simulacaoRequest.getQuantidadeCaminhoes38m3() != null) {
			adicionaVeiculo(simulacaoRequest, volumeUrna, depot, vehicleList, VOLUME_38M3);
		}
	}

	private void adicionaVeiculo(SimulacaoRequest simulacaoRequest, Double volumeUrna, Depot depot,
			List<Vehicle> vehicleList, float volumeVeiculo) {
		Long capacidade = Math.round(volumeVeiculo / volumeUrna);
		Supplier<Vehicle> vehicleSupplier = () -> new Vehicle(sequence.incrementAndGet(), capacidade.intValue(), depot);
		vehicleList.addAll(Stream.generate(vehicleSupplier).limit(simulacaoRequest.getQuantidadeCaminhoes38m3())
				.collect(Collectors.toList()));
	}

	private void adicionaVeiculos22m3(SimulacaoRequest simulacaoRequest, Double volumeUrna, Depot depot,
			List<Vehicle> vehicleList) {
		if (simulacaoRequest.getQuantidadeCaminhoes22m3() != null) {
			adicionaVeiculo(simulacaoRequest, volumeUrna, depot, vehicleList, VOLUME_22M3);
		}
	}

	private void adicionaVeiculos13m3(SimulacaoRequest simulacaoRequest, Double volumeUrna, Depot depot,
			List<Vehicle> vehicleList) {
		if (simulacaoRequest.getQuantidadeCaminhoes13m3() != null) {
			adicionaVeiculo(simulacaoRequest, volumeUrna, depot, vehicleList, VOLUME_13M3);
		}
	}

	private void adicionaVeiculos7_5m3(SimulacaoRequest simulacaoRequest, Double volumeUrna, Depot depot,
			List<Vehicle> vehicleList) {
		if (simulacaoRequest.getQuantidadeCaminhoes7_5m3() != null) {
			adicionaVeiculo(simulacaoRequest, volumeUrna, depot, vehicleList, VOLUME_7_5M3);
		}
	}

}
