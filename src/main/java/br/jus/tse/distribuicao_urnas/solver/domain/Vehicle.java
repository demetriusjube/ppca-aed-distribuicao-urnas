package br.jus.tse.distribuicao_urnas.solver.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.optaplanner.core.api.domain.lookup.PlanningId;

public class Vehicle implements Standstill {

	private static final long CONVERSOR_MINUTOS_MILIS = 60l * 1000l;
	private static final long CONVERSOR_HORA_MILIS = 60l * CONVERSOR_MINUTOS_MILIS;

	@PlanningId
	private long id;
	private String description;
	private int capacity;
	private Depot depot;

	private Customer nextVisit;

	public Vehicle() {
	}

	public Vehicle(long id, int capacity, Depot depot) {
		this(id, capacity, depot, "Veículo " + id);
	}

	public Vehicle(long id, int capacity, Depot depot, String description) {
		this.id = id;
		this.capacity = capacity;
		this.depot = depot;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	// ************************************************************************
	// Complex methods
	// ************************************************************************

	/**
	 * @return route of the vehicle
	 */
	public List<Location> getRoute() {
		Iterable<Customer> customers = getFutureVisits();
		if (!customers.iterator().hasNext()) {
			return Collections.emptyList();
		}

		List<Location> route = new ArrayList<Location>();

		route.add(depot.getLocation());
		for (Customer customer : customers) {
			route.add(customer.getLocation());
		}
		route.add(depot.getLocation());

		return route;
	}

	@Override
	public String toString() {
		return "Vehicle{" + "id=" + id + '}';
	}

	@Override
	public Location getLocation() {
		return depot.getLocation();
	}

	@Override
	public Customer getNextVisit() {
		return nextVisit;
	}

	@Override
	public void setNextVisit(Customer nextVisit) {
		this.nextVisit = nextVisit;
	}

	public Iterable<Customer> getFutureVisits() {
		return () -> new Iterator<Customer>() {
			Customer nextVisit = getNextVisit();

			@Override
			public boolean hasNext() {
				return nextVisit != null;
			}

			@Override
			public Customer next() {
				if (nextVisit == null) {
					throw new NoSuchElementException();
				}
				Customer out = nextVisit;
				nextVisit = nextVisit.getNextVisit();
				return out;
			}
		};
	}

	public long getTotalDistanceMeters() {
		Iterable<Customer> customers = getFutureVisits();
		if (!customers.iterator().hasNext()) {
			return 0;
		}

		long totalDistance = 0;
		Location previousLocation = depot.getLocation();
		for (Customer customer : customers) {
			totalDistance += previousLocation.getDistanceTo(customer.getLocation());
			previousLocation = customer.getLocation();
		}
		totalDistance += previousLocation.getDistanceTo(depot.getLocation());

		return totalDistance;
	}

	public long getTotalTimeMilis() {
		Iterable<Customer> customers = getFutureVisits();
		Customer lastCustomer = lastOrNull(customers.iterator());
		if (lastCustomer != null) {
			Long departureTime = lastCustomer.getDepartureTime();
			if (departureTime == null) {
				departureTime = calculateTotalDrivingTimeToLastVisit();
			}
			Location depotLocation = lastCustomer.getVehicle().getDepot().getLocation();
			long drivingTimeToDepot = lastCustomer.getLocation().getTimeTo(depotLocation);
			return departureTime + drivingTimeToDepot;
		}
		return 0;

	}

	private Long calculateTotalDrivingTimeToLastVisit() {
		Iterable<Customer> customers = getFutureVisits();
		long totalTime = 0;
		Location previousLocation = depot.getLocation();

		for (Customer customer : customers) {
			totalTime += previousLocation.getTimeTo(customer.getLocation());
			totalTime += customer.getServiceDuration();
			previousLocation = customer.getLocation();
		}
		return totalTime;

	}

	private Customer lastOrNull(Iterator<Customer> iterator) {
		Customer result = null;
		while (iterator.hasNext()) {
			result = iterator.next();
		}
		return result;
	}

	public int getTotalDemand() {
		int totalDemand = 0;
		Iterable<Customer> customers = getFutureVisits();
		if (customers.iterator().hasNext()) {
			for (Customer customer : customers) {
				totalDemand += customer.getDemand();
			}
		}
		return totalDemand;
	}

}
