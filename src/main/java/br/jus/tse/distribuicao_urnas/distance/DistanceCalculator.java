package br.jus.tse.distribuicao_urnas.distance;

import com.graphhopper.GHResponse;

import br.jus.tse.distribuicao_urnas.routing.Coordinates;

/**
 * Calculates distances between coordinates.
 */
public interface DistanceCalculator {

	GHResponse getRoutes(Coordinates from, Coordinates to);
}
