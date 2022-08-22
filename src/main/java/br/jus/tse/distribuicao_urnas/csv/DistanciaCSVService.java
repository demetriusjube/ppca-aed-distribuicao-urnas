package br.jus.tse.distribuicao_urnas.csv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.graphhopper.GHResponse;

import br.jus.tse.distribuicao_urnas.distance.DistanceCalculationException;
import br.jus.tse.distribuicao_urnas.distance.DistanceCalculator;
import br.jus.tse.distribuicao_urnas.domain.Distancia;
import br.jus.tse.distribuicao_urnas.domain.Localizacao;
import br.jus.tse.distribuicao_urnas.repos.DistanciaRepository;
import br.jus.tse.distribuicao_urnas.route.Router;
import br.jus.tse.distribuicao_urnas.routing.Coordinates;
import br.jus.tse.distribuicao_urnas.routing.GHRouteUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DistanciaCSVService {

	@Autowired
	private DistanciaRepository distanciaRepository;

	@Autowired
	private Router router;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void salvaDistancia(Localizacao origem, Localizacao destino) {
		try {
			if (!origem.getId().equals(destino.getId())) {
				GHResponse rotas = router.getRoutes(new Coordinates(origem.getLatitude(), origem.getLongitude()), new Coordinates(destino.getLatitude(), destino.getLongitude()));
				salvaDadosDaViagem(origem, destino, rotas);
			} else {
				salvaDadosDaViagem(origem, destino, null);
			}
		} catch (DistanceCalculationException e) {
			log.error("Não foi possível calcular a distância de {} para {} !", origem.getId(), destino.getId());
		}
	}

	private void salvaDadosDaViagem(Localizacao origem, Localizacao destino, GHResponse ghResponse) {
		Distancia distancia = new Distancia();
		distancia.setOrigem(origem);
		distancia.setDestino(destino);
		distancia.setMenorTempoViagem(GHRouteUtil.getMenorTempoEmMilis(ghResponse));
		distancia.setMenorDistancia(GHRouteUtil.getMenorDistanciaEmMetros(ghResponse));
		distanciaRepository.save(distancia);
	}

}
