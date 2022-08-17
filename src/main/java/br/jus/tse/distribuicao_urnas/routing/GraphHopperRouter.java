package br.jus.tse.distribuicao_urnas.routing;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.reader.osm.GraphHopperOSM;
import com.graphhopper.util.PointList;
import com.graphhopper.util.shapes.BBox;

import br.jus.tse.distribuicao_urnas.distance.DistanceCalculationException;
import br.jus.tse.distribuicao_urnas.distance.DistanceCalculator;
import br.jus.tse.distribuicao_urnas.domain.Localizacao;
import br.jus.tse.distribuicao_urnas.region.BoundingBox;
import br.jus.tse.distribuicao_urnas.region.Region;
import br.jus.tse.distribuicao_urnas.route.Router;

/**
 * Provides geographical information needed for route optimization.
 */
@Service
class GraphHopperRouter implements Router, DistanceCalculator, Region {

	private final GraphHopperOSM graphHopper;

	@Autowired
	GraphHopperRouter(GraphHopperOSM graphHopper) {
		this.graphHopper = graphHopper;
	}

	@Override
	public List<Localizacao> getPath(Localizacao from, Localizacao to) {
		GHRequest ghRequest = new GHRequest(from.getLatitude().doubleValue(), from.getLongitude().doubleValue(),
				to.getLatitude().doubleValue(), to.getLongitude().doubleValue());
		PointList points = graphHopper.route(ghRequest).getBest().getPoints();
		return StreamSupport.stream(points.spliterator(), false)
				.map(ghPoint3D -> Localizacao.of(ghPoint3D.lat, ghPoint3D.lon)).collect(toList());
	}

	@Override
	public long travelTimeMillis(Localizacao from, Localizacao to) {
		GHRequest ghRequest = new GHRequest(from.getLatitude().doubleValue(), from.getLongitude().doubleValue(),
				to.getLatitude().doubleValue(), to.getLongitude().doubleValue());
		GHResponse ghResponse = graphHopper.route(ghRequest);
		// TODO return wrapper that can hold both the result and error explanation
		// instead of throwing exception
		if (ghResponse.hasErrors()) {
			throw new DistanceCalculationException("No route from " + from + " to " + to,
					ghResponse.getErrors().get(0));
		}
		return ghResponse.getBest().getTime();
	}

	@Override
	public BoundingBox getBounds() {
		BBox bounds = graphHopper.getGraphHopperStorage().getBounds();
		return new BoundingBox(Localizacao.of(bounds.minLat, bounds.minLon),
				Localizacao.of(bounds.maxLat, bounds.maxLon));
	}
}
