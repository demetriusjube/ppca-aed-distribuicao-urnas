package br.jus.tse.distribuicao_urnas.rest;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.optaplanner.core.api.score.ScoreManager;
import org.optaplanner.core.api.score.buildin.hardsoftlong.HardSoftLongScore;
import org.optaplanner.core.api.solver.SolverManager;
import org.optaplanner.core.api.solver.SolverStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.tse.distribuicao_urnas.model.CentroDistribuicaoDTO;
import br.jus.tse.distribuicao_urnas.model.SimulacaoDTO;
import br.jus.tse.distribuicao_urnas.service.CentroDistribuicaoService;
import br.jus.tse.distribuicao_urnas.service.SimulacaoService;
import br.jus.tse.distribuicao_urnas.solver.builder.DepotCustomerBuilder;
import br.jus.tse.distribuicao_urnas.solver.builder.DistribuicaoUrnasSolutionBuilder;
import br.jus.tse.distribuicao_urnas.solver.domain.DepotCustomers;
import br.jus.tse.distribuicao_urnas.solver.domain.SimulacaoRequest;
import br.jus.tse.distribuicao_urnas.solver.domain.Status;
import br.jus.tse.distribuicao_urnas.solver.domain.VehicleRoutingSolution;
import br.jus.tse.distribuicao_urnas.solver.persistence.VehicleRoutingSolutionRepository;

@Transactional
@RestController
@RequestMapping(value = "/vrp")
public class SolverResource {

	private static final long PROBLEM_ID = 0L;
	private static final int TEMPO_DESCARREGAMENTO_DEFAULT = 30;

	private final AtomicReference<Throwable> solverError = new AtomicReference<>();

	private final VehicleRoutingSolutionRepository repository;
	private final SolverManager<VehicleRoutingSolution, Long> solverManager;
	private final ScoreManager<VehicleRoutingSolution, HardSoftLongScore> scoreManager;
	private final CentroDistribuicaoService centroDistribuicaoService;
	private final DistribuicaoUrnasSolutionBuilder distribuicaoUrnasSolutionBuilder;
	private final DepotCustomerBuilder depotCustomerBuilder;
	private final SimulacaoService simulacaoService;

	public SolverResource(VehicleRoutingSolutionRepository repository,
			SolverManager<VehicleRoutingSolution, Long> solverManager,
			ScoreManager<VehicleRoutingSolution, HardSoftLongScore> scoreManager,
			CentroDistribuicaoService centroDistribuicaoService,
			DistribuicaoUrnasSolutionBuilder distribuicaoUrnasSolutionBuilder,
			DepotCustomerBuilder depotCustomerBuilder, SimulacaoService simulacaoService) {
		this.repository = repository;
		this.solverManager = solverManager;
		this.scoreManager = scoreManager;
		this.centroDistribuicaoService = centroDistribuicaoService;
		this.distribuicaoUrnasSolutionBuilder = distribuicaoUrnasSolutionBuilder;
		this.depotCustomerBuilder = depotCustomerBuilder;
		this.simulacaoService = simulacaoService;
	}

	private Status statusFromSolution(VehicleRoutingSolution solution) {
		return statusFromSolution(solution, solverManager.getSolverStatus(PROBLEM_ID));
	}

	private Status statusFromSolution(VehicleRoutingSolution solution, SolverStatus solverStatus) {
		return new Status(solution, scoreManager.explainScore(solution).getSummary(), solverStatus);
	}

	@GetMapping(value = "centros-distribuicao", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CentroDistribuicaoDTO> getCentrosDistribuicao() {
		return centroDistribuicaoService.findAll();
	}

	@GetMapping(value = "depot-customers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public DepotCustomers getDepotAndCustomers(@PathVariable("id") Long idCentroDistribuicao) {
		return depotCustomerBuilder.build(idCentroDistribuicao, TEMPO_DESCARREGAMENTO_DEFAULT, 10);
	}

	@GetMapping(value = "status", produces = MediaType.APPLICATION_JSON_VALUE)
	public Status status() {
		Optional.ofNullable(solverError.getAndSet(null)).ifPresent(throwable -> {
			throw new RuntimeException("Solver failed", throwable);
		});

		Optional<VehicleRoutingSolution> s1 = repository.solution();

		VehicleRoutingSolution s = s1.orElse(VehicleRoutingSolution.empty());
		return statusFromSolution(s);
	}

	@PostMapping("solve")
	public void solve(@RequestBody SimulacaoRequest simulacaoRequest) {
		repository.update(distribuicaoUrnasSolutionBuilder.build(simulacaoRequest));
		Optional<VehicleRoutingSolution> maybeSolution = repository.solution();
		maybeSolution.ifPresent(
				vehicleRoutingSolution -> solverManager.solveAndListen(PROBLEM_ID, id -> vehicleRoutingSolution,
						repository::update, (problemId, throwable) -> solverError.set(throwable)));
	}

	@PostMapping("stopSolving")
	public void stopSolving() {
		solverManager.terminateEarly(PROBLEM_ID);
	}

	@GetMapping(value = "simulacoes", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SimulacaoDTO> getSimulacoes() {
		return simulacaoService.findAll();
	}

	@GetMapping(value = "simulacao/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Status getSimulacao(@PathVariable("id") Long idSimulacao) {
		return statusFromSolution(distribuicaoUrnasSolutionBuilder.build(idSimulacao), SolverStatus.NOT_SOLVING);
	}

}
