package br.jus.tse.distribuicao_urnas.route;

import java.util.List;

import com.graphhopper.GHResponse;

import br.jus.tse.distribuicao_urnas.model.TipoOtimizacaoEnum;
import br.jus.tse.distribuicao_urnas.routing.Coordinates;

/**
 * Provides paths between locations.
 */
public interface Router {

	List<Coordinates> getPath(Coordinates from, Coordinates to, TipoOtimizacaoEnum tipoOtimizacao);

	GHResponse getRoutes(Coordinates from, Coordinates to);

}
