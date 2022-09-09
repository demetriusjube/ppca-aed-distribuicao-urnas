package br.jus.tse.distribuicao_urnas.solver.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.AnchorShadowVariable;
import org.optaplanner.core.api.domain.variable.CustomShadowVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableGraphType;
import org.optaplanner.core.api.domain.variable.PlanningVariableReference;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.jus.tse.distribuicao_urnas.solver.ArrivalTimeUpdatingVariableListener;
import br.jus.tse.distribuicao_urnas.solver.DepotAngleCustomerDifficultyWeightFactory;

@PlanningEntity(difficultyWeightFactoryClass = DepotAngleCustomerDifficultyWeightFactory.class)
public class Customer implements Standstill {

	private static final long CONVERSOR_MINUTOS_MILIS = 60l * 1000l;

	@PlanningId
	private long id;
	private String name;
	private Location location;
	private int demand;
	private int tempoDescarregamentoMinutos;
	private long tempoDescarregamentoMilis;

	private long readyTime;
	private long dueTime;
	private long serviceDuration;

	// Shadow variable
	private Long arrivalTime;

	@JsonIgnore
	// Planning variable: changes during planning, between score calculations.
	@PlanningVariable(valueRangeProviderRefs = { "vehicleRange",
			"customerRange" }, graphType = PlanningVariableGraphType.CHAINED)
	private Standstill previousStandstill;

	@JsonIgnore
	private Customer nextVisit;
	@JsonIgnore
	@AnchorShadowVariable(sourceVariableName = "previousStandstill")
	private Vehicle vehicle;

	public Customer() {
	}

	public Customer(long id, String name, Location location, int demand, int tempoDescarregamentoMinutos) {
		this.id = id;
		this.name = name;
		this.location = location;
		this.demand = demand;
		this.readyTime = 0;
		setTempoDescarregamentoMinutos(tempoDescarregamentoMinutos);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getDemand() {
		return demand;
	}

	public void setDemand(int demand) {
		this.demand = demand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Standstill getPreviousStandstill() {
		return previousStandstill;
	}

	public void setPreviousStandstill(Standstill previousStandstill) {
		this.previousStandstill = previousStandstill;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public long getTempoDescarregamentoMilis() {
		return tempoDescarregamentoMilis;
	}

	public int getTempoDescarregamentoMinutos() {
		return tempoDescarregamentoMinutos;
	}

	/**
	 * @return a positive number, the time multiplied by 1000 to avoid floating
	 *         point arithmetic rounding errors
	 */
	public long getReadyTime() {
		return readyTime;
	}

	public void setReadyTime(long readyTime) {
		this.readyTime = readyTime;
	}

	/**
	 * @return a positive number, the time multiplied by 1000 to avoid floating
	 *         point arithmetic rounding errors
	 */
	public long getDueTime() {
		return dueTime;
	}

	public void setDueTime(long dueTime) {
		this.dueTime = dueTime;
	}

	/**
	 * @return a positive number, the time multiplied by 1000 to avoid floating
	 *         point arithmetic rounding errors
	 */
	public long getServiceDuration() {
		return serviceDuration;
	}

	public void setServiceDuration(long serviceDuration) {
		this.serviceDuration = serviceDuration;
	}

	/**
	 * @return a positive number, the time multiplied by 1000 to avoid floating
	 *         point arithmetic rounding errors
	 */
	@CustomShadowVariable(variableListenerClass = ArrivalTimeUpdatingVariableListener.class,
			// Arguable, to adhere to API specs (although this works), nextCustomer should
			// also be a source,
			// because this shadow must be triggered after nextCustomer (but there is no
			// need to be triggered by nextCustomer)
			sources = { @PlanningVariableReference(variableName = "previousStandstill") })
	public Long getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	// ************************************************************************
	// Complex methods
	// ************************************************************************

	@Override
	public String toString() {
		return "Customer{" + "id=" + id + '}';
	}

	@Override
	public Customer getNextVisit() {
		return this.nextVisit;
	}

	@Override
	public void setNextVisit(Customer nextVisit) {
		this.nextVisit = nextVisit;
	}

	public void setTempoDescarregamentoMinutos(int tempoDescarregamentoMinutos) {
		this.tempoDescarregamentoMinutos = tempoDescarregamentoMinutos;
		this.tempoDescarregamentoMilis = tempoDescarregamentoMinutos * CONVERSOR_MINUTOS_MILIS;
		this.serviceDuration = tempoDescarregamentoMilis;
	}

	// ************************************************************************
	// Complex methods
	// ************************************************************************

	/**
	 * Distance from the previous standstill to this visit. This is used to
	 * calculate the travel cost of a chain beginning with a vehicle (at a depot)
	 * and ending with the {@link #isLast() last} visit. The chain ends with a
	 * visit, not a depot so the cost of returning from the last visit back to the
	 * depot has to be added in a separate step using {@link #distanceToDepot()}.
	 *
	 * @return distance from previous standstill to this visit
	 */
	public Long distanceFromPreviousStandstill() {
		if (previousStandstill == null) {
			throw new IllegalStateException(
					"This method must not be called when the previousStandstill (null) is not initialized yet.");
		}
		return previousStandstill.getLocation().getDistanceTo(location);
	}

	public Long timeMilisFromPreviousStandstill() {
		if (previousStandstill == null) {
			throw new IllegalStateException(
					"This method must not be called when the previousStandstill (null) is not initialized yet.");
		}
		return tempoDescarregamentoMilis + previousStandstill.getLocation().getTimeTo(location);
	}

	/**
	 * Distance from this visit back to the depot.
	 *
	 * @return distance from this visit back its vehicle's depot
	 */
	public Long distanceToDepot() {
		return location.getDistanceTo(vehicle.getLocation());
	}

	@JsonIgnore
	public long getDistanceFromPreviousStandstill() {
		if (previousStandstill == null) {
			throw new IllegalStateException("This method must not be called when the previousStandstill ("
					+ previousStandstill + ") is not initialized yet.");
		}
		return getDistanceFrom(previousStandstill);
	}

	@JsonIgnore
	public long getDistanceFrom(Standstill standstill) {
		return standstill.getLocation().getDistanceTo(location);
	}

	/**
	 * Whether this visit is the last in a chain.
	 *
	 * @return true, if this visit has no {@link #getNextVisit() next} visit
	 */
	public boolean isLast() {
		return nextVisit == null;
	}

	public Long getDepartureTime() {
		if (arrivalTime == null) {
			return null;
		}
		return Math.max(arrivalTime, readyTime) + serviceDuration;
	}

	public boolean isArrivalBeforeReadyTime() {
		return arrivalTime != null && arrivalTime < readyTime;
	}

	public boolean isArrivalAfterDueTime() {
		return arrivalTime != null && dueTime < arrivalTime;
	}

	/**
	 * @return a positive number, the time multiplied by 1000 to avoid floating
	 *         point arithmetic rounding errors
	 */
	public long getTimeWindowGapTo(Customer other) {
		// dueTime doesn't account for serviceDuration
		long latestDepartureTime = dueTime + serviceDuration;
		long otherLatestDepartureTime = other.getDueTime() + other.getServiceDuration();
		if (latestDepartureTime < other.getReadyTime()) {
			return other.getReadyTime() - latestDepartureTime;
		}
		if (otherLatestDepartureTime < readyTime) {
			return readyTime - otherLatestDepartureTime;
		}
		return 0L;
	}

}
