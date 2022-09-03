package br.jus.tse.distribuicao_urnas.solver.domain;

import java.math.BigDecimal;
import java.util.Map;

import lombok.EqualsAndHashCode;

//@JsonFormat(shape = JsonFormat.Shape.ARRAY)
//@JsonIgnoreProperties({ "id" })
public class Location {

	private final long id;
	private final String nome;
	private final String endereco;
	private final BigDecimal latitude;
	private final BigDecimal longitude;
	private Map<Location, RouteData> distanceMap;

	public Location(long id, BigDecimal latitude, BigDecimal longitude, String endereco, String nome) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public long getId() {
		return id;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getNome() {
		return nome;
	}

	/**
	 * Set the distance map. Distances are in meters.
	 *
	 * @param distanceMap a map containing distances from here to other locations
	 */
	public void setDistanceMap(Map<Location, RouteData> distanceMap) {
		this.distanceMap = distanceMap;
	}

	/**
	 * Distance to the given location in meters.
	 *
	 * @param location other location
	 * @return distance in meters
	 */
	public Long getDistanceTo(Location location) {
		return distanceMap.get(location).getDistanceMeters();
	}

	/**
	 * Tempo de deslocamento até a outra localidade
	 *
	 * @param location other location
	 * @return distance in meters
	 */
	public Long getTimeTo(Location location) {
		return distanceMap.get(location).getDrivingTimeMilis();
	}

	// ************************************************************************
	// Complex methods
	// ************************************************************************

	/**
	 * The angle relative to the direction EAST.
	 *
	 * @param location never null
	 * @return in Cartesian coordinates
	 */
	public double getAngle(Location location) {
		// Euclidean distance (Pythagorean theorem) - not correct when the surface is a
		// sphere
		BigDecimal latitudeDifference = location.latitude.subtract(latitude);
		BigDecimal longitudeDifference = location.longitude.subtract(longitude);
		return Math.atan2(latitudeDifference.doubleValue(), longitudeDifference.doubleValue());
	}

}
