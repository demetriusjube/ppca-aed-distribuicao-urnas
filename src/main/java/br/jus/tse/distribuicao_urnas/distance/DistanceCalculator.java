package br.jus.tse.distribuicao_urnas.distance;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.graphhopper.GHResponse;

import br.jus.tse.distribuicao_urnas.model.TipoOtimizacaoEnum;
import br.jus.tse.distribuicao_urnas.routing.Coordinates;
import br.jus.tse.distribuicao_urnas.solver.domain.Location;

/**
 * Calculates distances between coordinates.
 */
public interface DistanceCalculator {

	/**
	 * Bulk calculation of distance. Typically much more scalable than
	 * {@link #calculateDistance(Location, Location)} iteratively.
	 *
	 * @param fromLocations never null
	 * @param toLocations   never null
	 * @return never null
	 */
	default Map<Location, Map<Location, Double>> calculateBulkDistance(Collection<Location> fromLocations,
			Collection<Location> toLocations, TipoOtimizacaoEnum tipoOtimizacaoEnum) {
		return fromLocations.stream()
				.collect(Collectors.toMap(Function.identity(), from -> toLocations.stream().collect(
						Collectors.toMap(Function.identity(), to -> calculateDistance(from, to, tipoOtimizacaoEnum)))));
	}

	Double calculateDistance(Location from, Location to, TipoOtimizacaoEnum tipoOtimizacao);

	/**
	 * Calculate distance matrix for the given list of locations and assign distance
	 * maps accordingly.
	 *
	 * @param locationList
	 */
	default void initDistanceMaps(Collection<Location> locationList, TipoOtimizacaoEnum tipoOtimizacaoEnum) {
		Map<Location, Map<Location, Double>> distanceMatrix = calculateBulkDistance(locationList, locationList, tipoOtimizacaoEnum);
		locationList.forEach(location -> location.setDistanceMap(distanceMatrix.get(location)));
	}
}
