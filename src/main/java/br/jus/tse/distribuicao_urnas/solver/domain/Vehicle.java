package br.jus.tse.distribuicao_urnas.solver.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningListVariable;

@PlanningEntity
public class Vehicle {

	private static final long CONVERSOR_MINUTOS_MILIS = 60l * 1000l;
	private static final long CONVERSOR_HORA_MILIS = 60l * CONVERSOR_MINUTOS_MILIS;
	private long id;
	private int capacity;
	private Depot depot;
	private int tempoDescarregamentoMinutos;
	private long tempoDescarregamentoMilis;
	private int tempoMaximoAtuacaoHoras;
	private long tempoMaximoAtuacaoMilis;

	@PlanningListVariable(valueRangeProviderRefs = "customerRange")
	private List<Customer> customerList;

	public Vehicle() {
	}

	public Vehicle(long id, int capacity, Depot depot, int tempoDescarregamentoMinutos, int tempoMaximoAtuacaoHoras) {
		this.id = id;
		this.capacity = capacity;
		this.depot = depot;
		this.customerList = new ArrayList<>();
		this.tempoDescarregamentoMinutos = tempoDescarregamentoMinutos;
		this.tempoDescarregamentoMilis = tempoDescarregamentoMinutos * CONVERSOR_MINUTOS_MILIS;
		this.tempoMaximoAtuacaoHoras = tempoMaximoAtuacaoHoras;
		this.tempoMaximoAtuacaoMilis = tempoMaximoAtuacaoHoras * CONVERSOR_HORA_MILIS;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	public int getTempoDescarregamentoMinutos() {
		return tempoDescarregamentoMinutos;
	}

	public void setTempoDescarregamentoMinutos(int tempoDescarregamentoMinutos) {
		this.tempoDescarregamentoMinutos = tempoDescarregamentoMinutos;
	}

	public int getTempoMaximoAtuacaoHoras() {
		return tempoMaximoAtuacaoHoras;
	}

	public void setTempoMaximoAtuacaoHoras(int tempoMaximoAtuacaoHoras) {
		this.tempoMaximoAtuacaoHoras = tempoMaximoAtuacaoHoras;
	}

	// ************************************************************************
	// Complex methods
	// ************************************************************************

	/**
	 * @return route of the vehicle
	 */
	public List<Location> getRoute() {
		if (customerList.isEmpty()) {
			return Collections.emptyList();
		}

		List<Location> route = new ArrayList<Location>();

		route.add(depot.getLocation());
		for (Customer customer : customerList) {
			route.add(customer.getLocation());
		}
		route.add(depot.getLocation());

		return route;
	}

	public int getTotalDemand() {
		int totalDemand = 0;
		for (Customer customer : customerList) {
			totalDemand += customer.getDemand();
		}
		return totalDemand;
	}

	public long getTotalDistanceMeters() {
		if (customerList.isEmpty()) {
			return 0;
		}

		long totalDistance = 0;
		Location previousLocation = depot.getLocation();

		for (Customer customer : customerList) {
			totalDistance += previousLocation.getDistanceTo(customer.getLocation());
			previousLocation = customer.getLocation();
		}
		totalDistance += previousLocation.getDistanceTo(depot.getLocation());

		return totalDistance;
	}

	public long getTotalTimeMilis() {
		if (customerList.isEmpty()) {
			return 0;
		}

		long totalTime = 0;
		Location previousLocation = depot.getLocation();

		for (Customer customer : customerList) {
			totalTime += previousLocation.getTimeTo(customer.getLocation());
			totalTime += tempoDescarregamentoMilis;
			previousLocation = customer.getLocation();
		}
		totalTime += previousLocation.getTimeTo(depot.getLocation());

		return totalTime;
	}

	public boolean isTempoMaximoAtuacaoUltrapassado() {
		return getTotalTimeMilis() > tempoMaximoAtuacaoMilis;
	}

	public long getTempoAtuacaoUltrapassadoMilis() {
		return getTotalTimeMilis() - tempoMaximoAtuacaoMilis;
	}

	@Override
	public String toString() {
		return "Vehicle{" + "id=" + id + '}';
	}
}
