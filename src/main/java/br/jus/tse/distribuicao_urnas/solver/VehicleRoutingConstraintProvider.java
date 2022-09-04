package br.jus.tse.distribuicao_urnas.solver;

import static org.optaplanner.core.api.score.stream.ConstraintCollectors.sumLong;
import static org.optaplanner.core.api.score.stream.ConstraintCollectors.sum;

import org.optaplanner.core.api.score.buildin.hardsoftlong.HardSoftLongScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;

import br.jus.tse.distribuicao_urnas.solver.domain.Customer;

public class VehicleRoutingConstraintProvider implements ConstraintProvider {

	@Override
	public Constraint[] defineConstraints(ConstraintFactory factory) {
		return new Constraint[] { vehicleCapacity(factory),// vehicleDrivingTime(factory), // totalTime(factory),
				distanceFromPreviousStandstill(factory), distanceFromLastVisitToDepot(factory) };
	}

//	private Constraint totalTime(ConstraintFactory factory) {
//		return factory.forEach(Vehicle.class).filter(vehicle -> vehicle.isTempoMaximoAtuacaoUltrapassado())
//				.penalizeLong("deliveryTime", HardSoftLongScore.ONE_HARD,
//						vehicle -> vehicle.getTempoAtuacaoUltrapassadoMinutos());
//	}

	// ************************************************************************
	// Hard constraints
	// ************************************************************************

//	protected Constraint vehicleCapacity(ConstraintFactory factory) {
//		return factory.forEach(Vehicle.class).filter(vehicle -> vehicle.getTotalDemand() > vehicle.getCapacity())
//				.penalizeLong("vehicleCapacity", HardSoftLongScore.ONE_HARD,
//						vehicle -> 100 * (vehicle.getTotalDemand() - vehicle.getCapacity()));
//	}

	Constraint vehicleCapacity(ConstraintFactory constraintFactory) {
		return constraintFactory.forEach(Customer.class).groupBy(Customer::getVehicle, sum(Customer::getDemand))
				.filter((vehicle, demand) -> demand > vehicle.getCapacity()).penalizeLong("vehicle capacity",
						HardSoftLongScore.ONE_HARD, (vehicle, demand) -> 100 * (demand - vehicle.getCapacity()));
	}

	Constraint vehicleDrivingTime(ConstraintFactory constraintFactory) {
		return constraintFactory.forEach(Customer.class)
				.groupBy(Customer::getVehicle, sumLong(Customer::timeMilisFromPreviousStandstill))
				.filter((vehicle, totalTime) -> totalTime > vehicle.getTempoMaximoAtuacaoMilis())
				.penalizeLong("vehicle driving time", HardSoftLongScore.ONE_HARD,
						(vehicle, totalTime) -> getTempoAtuacaoUltrapassadoMinutos(totalTime,
								vehicle.getTempoMaximoAtuacaoMilis()));
	}

	// ************************************************************************
	// Soft constraints
	// ************************************************************************

//	protected Constraint totalDistance(ConstraintFactory factory) {
//		return factory.forEach(Vehicle.class).penalizeLong("distanceFromPreviousStandstill", HardSoftLongScore.ONE_SOFT,
//				Vehicle::getTotalDistanceMeters);
//	}

	Constraint distanceFromPreviousStandstill(ConstraintFactory constraintFactory) {
		return constraintFactory.forEach(Customer.class).penalizeLong("distance from previous standstill",
				HardSoftLongScore.ONE_SOFT, Customer::distanceFromPreviousStandstill);
	}

	Constraint distanceFromLastVisitToDepot(ConstraintFactory constraintFactory) {
		return constraintFactory.forEach(Customer.class).filter(Customer::isLast).penalizeLong(
				"distance from last visit to depot", HardSoftLongScore.ONE_SOFT, Customer::distanceToDepot);
	}

	public long getTempoAtuacaoUltrapassadoMinutos(long totalTimeMilis, long tempoMaximoAtuacaoMilis) {
		return (totalTimeMilis - tempoMaximoAtuacaoMilis) / (60 * 1000);
	}

}
