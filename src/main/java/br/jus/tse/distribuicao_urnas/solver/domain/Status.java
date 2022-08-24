package br.jus.tse.distribuicao_urnas.solver.domain;

import org.optaplanner.core.api.solver.SolverStatus;

public class Status {

    public final VehicleRoutingSolution solution;
    public final String scoreExplanation;
    public final boolean isSolving;

    public Status(VehicleRoutingSolution solution, String scoreExplanation, SolverStatus solverStatus) {
        this.solution = solution;
        this.scoreExplanation = scoreExplanation;
        this.isSolving = solverStatus != SolverStatus.NOT_SOLVING;
    }
}
