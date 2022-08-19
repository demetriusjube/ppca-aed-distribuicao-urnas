package br.jus.tse.distribuicao_urnas.csv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.jus.tse.distribuicao_urnas.distance.DistanceCalculationException;
import br.jus.tse.distribuicao_urnas.distance.DistanceCalculator;
import br.jus.tse.distribuicao_urnas.domain.Distancia;
import br.jus.tse.distribuicao_urnas.domain.Localizacao;
import br.jus.tse.distribuicao_urnas.repos.DistanciaRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DistanciaCSVService {

	@Autowired
	private DistanciaRepository distanciaRepository;

	@Autowired
	private DistanceCalculator distanceCalculator;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void salvaDistancia(Localizacao origem, Localizacao destino) {
		try {
			if (!origem.getId().equals(destino.getId())) {
				Long tempoViagem = distanceCalculator.travelTimeMillis(origem, destino);
				salvaTempoDeViagem(origem, destino, tempoViagem);
			} else {
				salvaTempoDeViagem(origem, destino, 0l);
			}
		} catch (DistanceCalculationException e) {
			log.error("Não foi possível calcular a distância de {} para {} !", origem.getId(), destino.getId());
		}
	}

	private void salvaTempoDeViagem(Localizacao origem, Localizacao destino, Long tempoViagem) {
		Distancia distancia = new Distancia();
		distancia.setOrigem(origem);
		distancia.setDestino(destino);
		distancia.setTempoViagem(tempoViagem);
		distanciaRepository.save(distancia);
	}

}
