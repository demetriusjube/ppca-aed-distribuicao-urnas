package br.jus.tse.distribuicao_urnas.rest;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.optaplanner.core.api.score.ScoreManager;
import org.optaplanner.core.api.score.buildin.hardsoftlong.HardSoftLongScore;
import org.optaplanner.core.api.solver.SolverManager;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.tse.distribuicao_urnas.solver.domain.Status;
import br.jus.tse.distribuicao_urnas.solver.domain.VehicleRoutingSolution;
import br.jus.tse.distribuicao_urnas.solver.persistence.VehicleRoutingSolutionRepository;

@RestController
@RequestMapping(value = "/vrp")
public class SolverResource {

	private static final long PROBLEM_ID = 0L;

	private final AtomicReference<Throwable> solverError = new AtomicReference<>();

	private final VehicleRoutingSolutionRepository repository;
	private final SolverManager<VehicleRoutingSolution, Long> solverManager;
	private final ScoreManager<VehicleRoutingSolution, HardSoftLongScore> scoreManager;

	public SolverResource(VehicleRoutingSolutionRepository repository,
			SolverManager<VehicleRoutingSolution, Long> solverManager,
			ScoreManager<VehicleRoutingSolution, HardSoftLongScore> scoreManager) {
		this.repository = repository;
		this.solverManager = solverManager;
		this.scoreManager = scoreManager;
	}

	private Status statusFromSolution(VehicleRoutingSolution solution) {
		return new Status(solution, scoreManager.explainScore(solution).getSummary(),
				solverManager.getSolverStatus(PROBLEM_ID));
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
	public void solve() {
		Optional<VehicleRoutingSolution> maybeSolution = repository.solution();
		maybeSolution.ifPresent(
				vehicleRoutingSolution -> solverManager.solveAndListen(PROBLEM_ID, id -> vehicleRoutingSolution,
						repository::update, (problemId, throwable) -> solverError.set(throwable)));
	}

	@PostMapping("stopSolving")
	public void stopSolving() {
		solverManager.terminateEarly(PROBLEM_ID);
	}
}
