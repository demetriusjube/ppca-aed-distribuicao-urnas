package br.jus.tse.distribuicao_urnas.solver.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.AnchorShadowVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableGraphType;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	/**
	 * Whether this visit is the last in a chain.
	 *
	 * @return true, if this visit has no {@link #getNextVisit() next} visit
	 */
	public boolean isLast() {
		return nextVisit == null;
	}
}
