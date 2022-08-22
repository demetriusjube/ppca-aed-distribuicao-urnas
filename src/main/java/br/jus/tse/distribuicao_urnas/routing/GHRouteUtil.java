package br.jus.tse.distribuicao_urnas.routing;

import java.util.Comparator;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;

import com.graphhopper.GHResponse;
import com.graphhopper.PathWrapper;

public class GHRouteUtil {

	public static Long getMenorTempoEmMilis(GHResponse ghResponse) {
		if (ghResponse != null && ghResponse.getBest() != null) {
			return ghResponse.getBest().getTime();
		}
		return null;
	}

	public static Double getMenorDistanciaEmMetros(GHResponse ghResponse) {
		if (ghResponse != null && CollectionUtils.isNotEmpty(ghResponse.getAll())) {
			return getRotaMaisCurta(ghResponse).map(path -> path.getDistance()).orElse(null);
		}
		return null;
	}

	public static Optional<PathWrapper> getRotaMaisCurta(GHResponse ghResponse) {
		return ghResponse.getAll().stream().min(Comparator.comparing(PathWrapper::getDistance));
	}

}
