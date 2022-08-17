package br.jus.tse.distribuicao_urnas.route;

import java.util.List;

import br.jus.tse.distribuicao_urnas.domain.Localizacao;

/**
 * Provides paths between locations.
 */
public interface Router {

	/**
	 * Get path between two locations.
	 *
	 * @param from starting location
	 * @param to   destination
	 * @return list of coordinates describing the path between given locations.
	 */
	List<Localizacao> getPath(Localizacao from, Localizacao to);
}
