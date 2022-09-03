package br.jus.tse.distribuicao_urnas.solver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphhopper.GHResponse;
import com.graphhopper.PathWrapper;

import br.jus.tse.distribuicao_urnas.distance.DistanceCalculationException;
import br.jus.tse.distribuicao_urnas.distance.DistanceCalculator;
import br.jus.tse.distribuicao_urnas.model.TipoOtimizacaoEnum;
import br.jus.tse.distribuicao_urnas.route.Router;
import br.jus.tse.distribuicao_urnas.routing.Coordinates;
import br.jus.tse.distribuicao_urnas.routing.GHRouteUtil;
import br.jus.tse.distribuicao_urnas.solver.domain.Location;
import br.jus.tse.distribuicao_urnas.solver.domain.RouteData;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CalculadorDistanciaService implements DistanceCalculator {

	@Autowired
	private Router router;

	@Override
	public RouteData calculateDistance(Location from, Location to, TipoOtimizacaoEnum tipoOtimizacao) {
		try {
			Coordinates coordenadasOrigem = new Coordinates(from.getLatitude(), from.getLongitude());
			Coordinates coordenadasDestino = new Coordinates(to.getLatitude(), to.getLongitude());
			GHResponse routes = router.getRoutes(coordenadasOrigem, coordenadasDestino);
			PathWrapper path = routes.getBest();
			if (TipoOtimizacaoEnum.MENOR_DISTANCIA.equals(tipoOtimizacao)) {
				path = GHRouteUtil.getPathMenorDistanciaEmMetros(routes);
			}
			if (path == null) {
				throw new DistanceCalculationException(
						"Não foi possível recuperar o trajeto de " + from.toString() + " para " + to.toString());
			}
			return new RouteData(getDistanceLong(path.getDistance()), path.getTime());
		} catch (DistanceCalculationException e) {
			log.warn(e.getMessage());
			return null;
		}
	}
	
	private long getDistanceLong(double distance) {
		return (long) Math.ceil(distance);
	}

}
