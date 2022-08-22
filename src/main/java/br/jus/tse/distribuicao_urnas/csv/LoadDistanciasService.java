package br.jus.tse.distribuicao_urnas.csv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.tse.distribuicao_urnas.domain.Localizacao;
import br.jus.tse.distribuicao_urnas.repos.DistanciaRepository;
import br.jus.tse.distribuicao_urnas.repos.LocalizacaoRepository;
import lombok.extern.slf4j.Slf4j;

@Profile("load-distancias")
@Service
@Slf4j
public class LoadDistanciasService {

	@Autowired
	private LocalizacaoRepository localizacaoRepository;

	@Autowired
	private DistanciaCSVService distanciaCsvService;

	@Autowired
	private DistanciaRepository distanciaRepository;

	@EventListener
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			calcularDistanciasEntreLocais();
		} catch (IllegalStateException e) {
			throw new RuntimeException("Erro ao importar os locais de votacao!");
		}
	}

	private void calcularDistanciasEntreLocais() {
		List<Localizacao> origens = localizacaoRepository.findAll();
		List<Localizacao> destinos = new ArrayList<Localizacao>(origens);
		for (Localizacao origem : origens) {
			for (Localizacao destino : destinos) {
				boolean distanciaSalva = distanciaRepository.existsByOrigemEqualsAndDestinoEquals(origem, destino);
				if (!distanciaSalva) {
					distanciaCsvService.salvaDistancia(origem, destino);
				}
			}
		}
	}

}
