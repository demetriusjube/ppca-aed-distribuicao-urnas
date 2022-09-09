package br.jus.tse.distribuicao_urnas.solver.domain;

public class Depot {

	private final long id;
	private final String name;
	private final Location location;
	private long readyTime;
	private long dueTime;

	public Depot(long id, Location location, String name, long readyTime, long dueTime) {
		this.id = id;
		this.name = name;
		this.location = location;
		this.readyTime = readyTime;
		this.dueTime = dueTime;
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

	public long getReadyTime() {
		return readyTime;
	}

	public void setReadyTime(long readyTime) {
		this.readyTime = readyTime;
	}

	public long getDueTime() {
		return dueTime;
	}

	public void setDueTime(long dueTime) {
		this.dueTime = dueTime;
	}
}
