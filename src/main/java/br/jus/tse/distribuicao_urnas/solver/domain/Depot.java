package br.jus.tse.distribuicao_urnas.solver.domain;

public class Depot {

	private final long id;
	private final String name;
	private final Location location;

	public Depot(long id, Location location, String name) {
		this.id = id;
		this.name = name;
		this.location = location;
	}

	public long getId() {
		return id;
	}

	public Location getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}
}
