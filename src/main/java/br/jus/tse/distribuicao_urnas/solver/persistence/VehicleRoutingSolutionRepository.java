package br.jus.tse.distribuicao_urnas.solver.persistence;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.jus.tse.distribuicao_urnas.solver.domain.VehicleRoutingSolution;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class VehicleRoutingSolutionRepository {

	private VehicleRoutingSolution vehicleRoutingSolution;

	public Optional<VehicleRoutingSolution> solution() {
		return Optional.ofNullable(vehicleRoutingSolution);
	}

	public void update(VehicleRoutingSolution vehicleRoutingSolution) {
		this.vehicleRoutingSolution = vehicleRoutingSolution;
		log.info("Nova solução encontrada! ");
	}
}
