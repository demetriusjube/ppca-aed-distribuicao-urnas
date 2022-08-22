package br.jus.tse.distribuicao_urnas.solver.domain;

import java.math.BigDecimal;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@JsonIgnoreProperties({ "id" })
public class Location {

	private final long id;
	private final BigDecimal latitude;
	private final BigDecimal longitude;
	private Map<Location, Double> distanceMap;

	public Location(long id, BigDecimal latitude, BigDecimal longitude) {
		this.id = id;
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

	/**
	 * Set the distance map. Distances are in meters.
	 *
	 * @param distanceMap a map containing distances from here to other locations
	 */
	public void setDistanceMap(Map<Location, Double> distanceMap) {
		this.distanceMap = distanceMap;
	}

	/**
	 * Distance to the given location in meters.
	 *
	 * @param location other location
	 * @return distance in meters
	 */
	public Double getDistanceTo(Location location) {
		return distanceMap.get(location);
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
