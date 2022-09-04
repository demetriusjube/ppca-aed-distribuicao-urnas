package br.jus.tse.distribuicao_urnas.solver;

import org.optaplanner.core.impl.heuristic.selector.common.nearby.NearbyDistanceMeter;

import br.jus.tse.distribuicao_urnas.solver.domain.Customer;
import br.jus.tse.distribuicao_urnas.solver.domain.Standstill;

public class CustomerNearbyDistanceMeter implements NearbyDistanceMeter<Customer, Standstill> {

	@Override
	public double getNearbyDistance(Customer origin, Standstill destination) {
		return origin.getLocation().getDistanceTo(destination.getLocation());
	}

}
