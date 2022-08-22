package br.jus.tse.distribuicao_urnas.solver.persistence;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.jus.tse.distribuicao_urnas.solver.domain.VehicleRoutingSolution;

@Component
public class VehicleRoutingSolutionRepository {

    private VehicleRoutingSolution vehicleRoutingSolution;

    public Optional<VehicleRoutingSolution> solution() {
        return Optional.ofNullable(vehicleRoutingSolution);
    }

    public void update(VehicleRoutingSolution vehicleRoutingSolution) {
        this.vehicleRoutingSolution = vehicleRoutingSolution;
    }
}
