package br.jus.tse.distribuicao_urnas.distance;

import br.jus.tse.distribuicao_urnas.domain.Localizacao;

/**
 * Calculates distances between coordinates.
 */
public interface DistanceCalculator {

    /**
     * Calculate travel time in milliseconds.
     *
     * @param from origin
     * @param to destination
     * @return travel time in milliseconds
     * @throws DistanceCalculationException when the distance between given coordinates cannot be calculated
     */
    long travelTimeMillis(Localizacao from, Localizacao to);
}
