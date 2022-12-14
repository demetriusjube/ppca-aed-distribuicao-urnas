package br.jus.tse.distribuicao_urnas.solver.domain;

import java.util.ArrayList;
import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoftlong.HardSoftLongScore;

@PlanningSolution
public class VehicleRoutingSolution {

	private String name;
	@ProblemFactCollectionProperty
	private List<Depot> depotList = new ArrayList<Depot>();

	@PlanningEntityCollectionProperty
	@ValueRangeProvider(id = "vehicleRange")
	private List<Vehicle> vehicleList = new ArrayList<Vehicle>();

	@PlanningEntityCollectionProperty
	@ValueRangeProvider(id = "customerRange")
	private List<Customer> customerList = new ArrayList<Customer>();

	@PlanningScore
	private HardSoftLongScore score;

	public VehicleRoutingSolution() {
	}

	public VehicleRoutingSolution(String name, List<Depot> depotList, List<Vehicle> vehicleList,
			List<Customer> customerList) {
		this.name = name;
		this.depotList = depotList;
		this.vehicleList = vehicleList;
		this.customerList = customerList;
	}

	public static VehicleRoutingSolution empty() {
		VehicleRoutingSolution problem = new VehicleRoutingSolution();

		problem.setScore(HardSoftLongScore.ZERO);

		return problem;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Depot> getDepotList() {
		return depotList;
	}

	public void setDepotList(List<Depot> depotList) {
		this.depotList = depotList;
	}

	public List<Vehicle> getVehicleList() {
		return vehicleList;
	}

	public void setVehicleList(List<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	public HardSoftLongScore getScore() {
		return score;
	}

	public void setScore(HardSoftLongScore score) {
		this.score = score;
	}

	// ************************************************************************
	// Complex methods
	// ************************************************************************

	public long getDistanceMeters() {
		return score == null ? 0 : -score.getSoftScore();
	}
}
