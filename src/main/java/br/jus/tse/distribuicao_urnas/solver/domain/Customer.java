package br.jus.tse.distribuicao_urnas.solver.domain;

public class Customer {

	private long id;
	private String name;
	private Location location;
	private int demand;

	public Customer() {
	}

	public Customer(long id, String name, Location location, int demand) {
		this.id = id;
		this.name = name;
		this.location = location;
		this.demand = demand;
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

	// ************************************************************************
	// Complex methods
	// ************************************************************************

	@Override
	public String toString() {
		return "Customer{" + "id=" + id + '}';
	}
}
