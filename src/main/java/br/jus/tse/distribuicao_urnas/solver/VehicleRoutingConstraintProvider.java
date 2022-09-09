package br.jus.tse.distribuicao_urnas.solver;

import static org.optaplanner.core.api.score.stream.ConstraintCollectors.sum;

import org.optaplanner.core.api.score.buildin.hardsoftlong.HardSoftLongScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;

import br.jus.tse.distribuicao_urnas.solver.domain.Customer;
import br.jus.tse.distribuicao_urnas.solver.domain.Depot;

public class VehicleRoutingConstraintProvider implements ConstraintProvider {

	@Override
	public Constraint[] defineConstraints(ConstraintFactory factory) {
		return new Constraint[] { vehicleCapacity(factory), distanceFromPreviousStandstill(factory),
				distanceFromLastVisitToDepot(factory), arrivalAfterDueTime(factory) };
	}

	// ************************************************************************
	// Hard constraints
	// ************************************************************************

	Constraint vehicleCapacity(ConstraintFactory constraintFactory) {
		return constraintFactory.forEach(Customer.class).groupBy(Customer::getVehicle, sum(Customer::getDemand))
				.filter((vehicle, demand) -> demand > vehicle.getCapacity()).penalizeLong("vehicle capacity",
						HardSoftLongScore.ONE_HARD, (vehicle, demand) -> 100 * (demand - vehicle.getCapacity()));
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

	protected Constraint arrivalAfterDueTime(ConstraintFactory factory) {
//		return factory.forEach(Customer.class).filter(customer -> customer.getArrivalTime() > customer.getDueTime())
//				.penalizeLong("arrivalAfterDueTime", HardSoftLongScore.ONE_HARD,
//						customer -> customer.getArrivalTime() - customer.getDueTime());
		return factory.forEach(Customer.class).filter(Customer::isLast)
				.filter(customer -> vaiUltrapassarTempoMaximo(customer)).penalizeLong("depot max arrival time",
						HardSoftLongScore.ONE_HARD, customer -> diferencaTempoChegadaMinutos(customer));

	}

	private boolean vaiUltrapassarTempoMaximo(Customer customer) {
		long diferencaTempoChegada = diferencaTempoChegadaMinutos(customer);
		return diferencaTempoChegada >= 1;
	}

	private long diferencaTempoChegadaMinutos(Customer customer) {
		Depot depot = customer.getVehicle().getDepot();
		long tempoMaximo = depot.getDueTime();
		long horaSaida = customer.getDepartureTime();
		long tempoViagem = customer.getLocation().getTimeTo(depot.getLocation());
		long horaChegadaDeposito = horaSaida + tempoViagem;
		return (horaChegadaDeposito - tempoMaximo) / (60 * 1000);
	}

}
