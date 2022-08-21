package br.jus.tse.distribuicao_urnas.route;

import java.util.List;

import br.jus.tse.distribuicao_urnas.routing.Coordinates;

/**
 * Provides paths between locations.
 */
public interface Router {

	List<Coordinates> getPath(Coordinates from, Coordinates to);
}
