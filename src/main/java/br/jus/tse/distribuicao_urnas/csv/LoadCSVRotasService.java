package br.jus.tse.distribuicao_urnas.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opencsv.bean.CsvToBeanBuilder;

import br.jus.tse.distribuicao_urnas.domain.CentroDistribuicao;
import br.jus.tse.distribuicao_urnas.domain.LocalVotacao;
import br.jus.tse.distribuicao_urnas.domain.PlanoRota;
import br.jus.tse.distribuicao_urnas.domain.RestricaoSimulacao;
import br.jus.tse.distribuicao_urnas.domain.Simulacao;
import br.jus.tse.distribuicao_urnas.domain.Veiculo;
import br.jus.tse.distribuicao_urnas.domain.VeiculoSimulacao;
import br.jus.tse.distribuicao_urnas.domain.Visita;
import br.jus.tse.distribuicao_urnas.domain.ZonaEleitoral;
import br.jus.tse.distribuicao_urnas.model.TipoOtimizacaoEnum;
import br.jus.tse.distribuicao_urnas.model.TipoRestricaoEnum;
import br.jus.tse.distribuicao_urnas.repos.CentroDistribuicaoRepository;
import br.jus.tse.distribuicao_urnas.repos.LocalVotacaoRepository;
import br.jus.tse.distribuicao_urnas.repos.PlanoRotaRepository;
import br.jus.tse.distribuicao_urnas.repos.RestricaoSimulacaoRepository;
import br.jus.tse.distribuicao_urnas.repos.SimulacaoRepository;
import br.jus.tse.distribuicao_urnas.repos.VeiculoRepository;
import br.jus.tse.distribuicao_urnas.repos.VeiculoSimulacaoRepository;
import br.jus.tse.distribuicao_urnas.repos.VisitaRepository;
import lombok.extern.slf4j.Slf4j;

@Profile("load-rotas-existentes")
@Service
@Slf4j
public class LoadCSVRotasService {

	private static final int CAPACIDADE_7_5M3 = 81;

	private static final int CAPACIDADE_15M3 = 162;

	private static final int CAPACIDADE_38M3 = 488;

	private static final int CAPACIDADE_22M3 = 280;

	@Autowired
	private LocalVotacaoRepository localVotacaoRepository;

	@Autowired
	private CentroDistribuicaoRepository centroDistribuicaoRepository;

	@Autowired
	private SimulacaoRepository simulacaoRepository;

	@Autowired
	private VeiculoSimulacaoRepository veiculoSimulacaoRepository;

	@Autowired
	private VeiculoRepository veiculoRepository;

	@Autowired
	private PlanoRotaRepository planoRotaRepository;

	@Autowired
	private VisitaRepository visitaRepository;

	@Autowired
	private RestricaoSimulacaoRepository restricaoSimulacaoRepository;

	@EventListener
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			importarRotasExistentes();
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException("Erro ao importar os locais de votacao!");
		}
	}

	private void importarRotasExistentes() throws IllegalStateException, FileNotFoundException, IOException {
		List<RotaExistenteCSVDto> rotasExistentesCSV = new CsvToBeanBuilder(
				new FileReader(getArquivoRotaExistenteCSV())).withType(RotaExistenteCSVDto.class).withQuoteChar('\"')
				.build().parse();
		log.info("Foram recuperados {} registros de rota", rotasExistentesCSV.size());
		List<CentroDistribuicao> centrosDistribuicao = centroDistribuicaoRepository.findAll();
		for (CentroDistribuicao centroDistribuicao : centrosDistribuicao) {
			Simulacao simulacao = montaSimulacao(centroDistribuicao);

			simulacaoRepository.save(simulacao);
			List<Integer> zonasEleitoraisDoCentro = centroDistribuicao.getCentroDistribuicaoZonaEleitorals().stream()
					.map(ZonaEleitoral::getNumero).toList();
			for (Integer numeroZE : zonasEleitoraisDoCentro) {
				List<RotaExistenteCSVDto> rotasZonaEleitoral = rotasExistentesCSV.stream()
						.filter(rotaExistente -> rotaExistente.getNumeroZE().equals(numeroZE)).toList();
				Map<Integer, List<RotaExistenteCSVDto>> mapaRotas = new HashMap<Integer, List<RotaExistenteCSVDto>>();
				for (RotaExistenteCSVDto rotaZonaEleitoral : rotasZonaEleitoral) {
					if (!mapaRotas.containsKey(rotaZonaEleitoral.getRota())) {
						mapaRotas.put(rotaZonaEleitoral.getRota(), new ArrayList<RotaExistenteCSVDto>());
					}
					mapaRotas.get(rotaZonaEleitoral.getRota()).add(rotaZonaEleitoral);
				}

				for (Integer rotaZonaEleitoral : mapaRotas.keySet()) {
					int quantidadeDeUrnas = 0;
					PlanoRota planoRota = new PlanoRota();
					planoRotaRepository.save(planoRota);
					List<RotaExistenteCSVDto> rotasOrdenadas = mapaRotas.get(rotaZonaEleitoral).stream()
							.sorted(Comparator.comparing(RotaExistenteCSVDto::getParada)).toList();
					int ordem = 1;
					for (RotaExistenteCSVDto rota : rotasOrdenadas) {
						LocalVotacao localVotacao = localVotacaoRepository
								.findByNumeroEqualsAndZonaEleitoralNumeroEquals(rota.getNumeroLocalVotacao(),
										rota.getNumeroZE())
								.orElse(null);
						if (localVotacao != null) {
							Visita visita = montaVisita(planoRota, ordem, localVotacao);
							visitaRepository.save(visita);
							ordem++;
							quantidadeDeUrnas += localVotacao.getQuantidadeSecoes();
						} else {
							log.error(
									"Não foi possível encontrar o local de votação {} da Zona Eleitoral {} ! Possível inconsistência!",
									rota.getNumeroLocalVotacao(), rota.getNumeroZE());
							throw new RuntimeException("Falha ao importar as rotas!");
						}
					}

					Veiculo veiculo = salvaVeiculo(numeroZE, rotaZonaEleitoral, quantidadeDeUrnas);
					salvaVeiculoSimulacao(simulacao, quantidadeDeUrnas, planoRota, veiculo);

				}
				salvaRestricoesPadrao(simulacao);
			}
		}

	}

	private Visita montaVisita(PlanoRota planoRota, int ordem, LocalVotacao localVotacao) {
		Visita visita = new Visita();
		visita.setLocalVotacao(localVotacao);
		visita.setOrdem(ordem);
		visita.setPlanoRota(planoRota);
		return visita;
	}

	private Simulacao montaSimulacao(CentroDistribuicao centroDistribuicao) {
		Simulacao simulacao = new Simulacao();
		simulacao.setCentroDistribuicao(centroDistribuicao);
		simulacao.setDataHora(LocalDateTime.now());
		simulacao.setDescricao("Rota existente do Centro de Distribuição " + centroDistribuicao.getNome());
		simulacao.setTipoOtimizacao(TipoOtimizacaoEnum.MENOR_DISTANCIA);
		return simulacao;
	}

	private void salvaVeiculoSimulacao(Simulacao simulacao, int quantidadeDeUrnas, PlanoRota planoRota,
			Veiculo veiculo) {
		VeiculoSimulacao veiculoSimulacao = new VeiculoSimulacao();
		veiculoSimulacao.setUrnasTransportadas(quantidadeDeUrnas);
		veiculoSimulacao.setPlanoRota(planoRota);
		veiculoSimulacao.setSimulacao(simulacao);
		veiculoSimulacao.setVeiculo(veiculo);
		veiculoSimulacaoRepository.save(veiculoSimulacao);
	}

	private Veiculo salvaVeiculo(Integer numeroZE, Integer rotaZonaEleitoral, int quantidadeDeUrnas) {
		Veiculo veiculo = new Veiculo();
		veiculo.setDescricao("Veículo - Zona Eleitoral " + numeroZE + " - Rota " + rotaZonaEleitoral);
		veiculo.setCapacidade(getCapacidadePeloNumeroDeUrnasTransportadas(quantidadeDeUrnas));
		veiculoRepository.save(veiculo);
		return veiculo;
	}

	private void salvaRestricoesPadrao(Simulacao simulacao) {
		RestricaoSimulacao restricaoSimulacao = new RestricaoSimulacao();
		restricaoSimulacao.setTipoRestricao(TipoRestricaoEnum.TEMPO_MAXIMO_DISTRIBUICAO);
		restricaoSimulacao.setValor(10d);
		restricaoSimulacao.setRestricoes(simulacao);
		restricaoSimulacaoRepository.save(restricaoSimulacao);
		RestricaoSimulacao restricaoSimulacaoTempoDescarregamento = new RestricaoSimulacao();
		restricaoSimulacaoTempoDescarregamento.setTipoRestricao(TipoRestricaoEnum.VALOR_MAXIMO_GASTO);
		restricaoSimulacaoTempoDescarregamento.setValor(30d);
		restricaoSimulacaoTempoDescarregamento.setRestricoes(simulacao);
		restricaoSimulacaoRepository.save(restricaoSimulacaoTempoDescarregamento);
	}

	private Integer getCapacidadePeloNumeroDeUrnasTransportadas(int quantidadeDeUrnas) {
		if (quantidadeDeUrnas > CAPACIDADE_22M3) {
			return CAPACIDADE_38M3;
		} else if (quantidadeDeUrnas > CAPACIDADE_15M3) {
			return CAPACIDADE_22M3;
		} else if (quantidadeDeUrnas > CAPACIDADE_7_5M3) {
			return CAPACIDADE_15M3;
		}
		return CAPACIDADE_7_5M3;
	}

	private File getArquivoRotaExistenteCSV() throws IOException {
		return getArquivoDoClasspath("csv/rotas-existentes.csv");
	}

	private File getArquivoDoClasspath(String caminhoArquivo) throws IOException {
		return new ClassPathResource(caminhoArquivo).getFile();
	}

}
