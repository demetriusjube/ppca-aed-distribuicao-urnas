package br.jus.tse.distribuicao_urnas.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
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
import br.jus.tse.distribuicao_urnas.domain.Localizacao;
import br.jus.tse.distribuicao_urnas.domain.ParametroCalculo;
import br.jus.tse.distribuicao_urnas.domain.TRE;
import br.jus.tse.distribuicao_urnas.domain.ZonaEleitoral;
import br.jus.tse.distribuicao_urnas.repos.CentroDistribuicaoRepository;
import br.jus.tse.distribuicao_urnas.repos.LocalVotacaoRepository;
import br.jus.tse.distribuicao_urnas.repos.LocalizacaoRepository;
import br.jus.tse.distribuicao_urnas.repos.ParametroCalculoRepository;
import br.jus.tse.distribuicao_urnas.repos.TRERepository;
import br.jus.tse.distribuicao_urnas.repos.ZonaEleitoralRepository;
import lombok.extern.slf4j.Slf4j;

@Profile("load-locais-votacao")
@Service
@Slf4j
public class LoadCSVService {

	@Autowired
	private LocalVotacaoRepository localVotacaoRepository;

	@Autowired
	private ZonaEleitoralRepository zonaEleitoralRepository;

	@Autowired
	private LocalizacaoRepository localizacaoRepository;

	@Autowired
	private TRERepository treRepository;

	@Autowired
	private CentroDistribuicaoRepository centroDistribuicaoRepository;

	@Autowired
	private ParametroCalculoRepository parametroCalculoRepository;

	@Transactional
	public CentroDistribuicao saveCentroDistribuicaoFromCSV(CentroDistribuicaoCSVDto centroDistribuicaoCSVDto) {

		Optional<CentroDistribuicao> centroDistribuicaoSalvo = centroDistribuicaoRepository
				.findByEnderecoEquals(centroDistribuicaoCSVDto.getEndereco());
		if (centroDistribuicaoSalvo.isPresent()) {
			log.info("CENTRO DE DISTRIBUI????O {} J?? CADASTRADO!", centroDistribuicaoCSVDto.getNome());
			return centroDistribuicaoSalvo.get();
		}
		log.info("Iniciando cadastro do Centro de distribui????o ");
		CentroDistribuicao centroDistribuicao = montaCentroDistribuicao(centroDistribuicaoCSVDto);
		centroDistribuicaoRepository.save(centroDistribuicao);
		return centroDistribuicao;

	}

	private CentroDistribuicao montaCentroDistribuicao(CentroDistribuicaoCSVDto centroDistribuicaoCSVDto) {
		CentroDistribuicao centroDistribuicao = new CentroDistribuicao();
		centroDistribuicao.setEndereco(centroDistribuicaoCSVDto.getEndereco());
		Localizacao localizacao = getLocalizacao(centroDistribuicaoCSVDto.getLatitude(),
				centroDistribuicaoCSVDto.getLongitude());
		centroDistribuicao.setLocalizacao(localizacao);
		centroDistribuicao.setNome(centroDistribuicaoCSVDto.getNome());
		centroDistribuicaoRepository.save(centroDistribuicao);
		if (CollectionUtils.isNotEmpty(centroDistribuicaoCSVDto.getZonasEleitorais())) {
			for (Integer numeroZE : centroDistribuicaoCSVDto.getZonasEleitorais()) {
				Optional<ZonaEleitoral> zonaEleitoralSalva = zonaEleitoralRepository.findByNumero(numeroZE);
				if (zonaEleitoralSalva.isPresent()) {
					ZonaEleitoral zonaEleitoral = zonaEleitoralSalva.get();
					zonaEleitoral.setCentroDistribuicao(centroDistribuicao);
					zonaEleitoralRepository.save(zonaEleitoral);
				}
			}
		}
		return centroDistribuicao;
	}

	@Transactional
	public LocalVotacao saveLocalVotacaoFromCSV(LocalVotacaoCSVDto localVotacaoCSVDto) {

		Optional<LocalVotacao> localVotacaoSalvo = localVotacaoRepository
				.findByNumeroEqualsAndZonaEleitoralNumeroEquals(localVotacaoCSVDto.getNumeroLocalVotacao(),
						localVotacaoCSVDto.getNumeroZE());
		if (localVotacaoSalvo.isPresent()) {
			log.info("LOCAL DE VOTA????O N?? {} {} J?? CADASTRADO!", localVotacaoCSVDto.getNumeroLocalVotacao(),
					localVotacaoCSVDto.getNome());
			return localVotacaoSalvo.get();
		}
		log.info("Iniciando cadastro do local de vota????o N?? {} {} ");
		LocalVotacao localVotacao = montaLocalVotacao(localVotacaoCSVDto);
		localVotacaoRepository.save(localVotacao);
		return localVotacao;

	}

	private LocalVotacao montaLocalVotacao(LocalVotacaoCSVDto localVotacaoCSVDto) {
		ZonaEleitoral zonaEleitoral = getZonaEleitoral(localVotacaoCSVDto);
		Localizacao localizacao = getLocalizacao(localVotacaoCSVDto.getLatitude(), localVotacaoCSVDto.getLongitude());
		LocalVotacao localVotacao = new LocalVotacao();
		localVotacao.setEndereco(localVotacaoCSVDto.getEndereco());
		localVotacao.setLocalizacao(localizacao);
		localVotacao.setNome(localVotacaoCSVDto.getNome());
		localVotacao.setNumero(localVotacaoCSVDto.getNumeroLocalVotacao());
		localVotacao.setQuantidadeSecoes(localVotacaoCSVDto.getQuantidadeSecoes());
		localVotacao.setZonaEleitoral(zonaEleitoral);
		return localVotacao;
	}

	private Localizacao getLocalizacao(BigDecimal latitude, BigDecimal longitude) {
		Localizacao localizacao;

		Optional<Localizacao> localizacaoSalva = localizacaoRepository.findByLatitudeEqualsAndLongitudeEquals(latitude,
				longitude);
		if (localizacaoSalva.isEmpty()) {
			localizacao = new Localizacao();
			localizacao.setLatitude(latitude);
			localizacao.setLongitude(longitude);
			localizacaoRepository.save(localizacao);
		} else {
			localizacao = localizacaoSalva.get();
		}
		return localizacao;
	}

	private ZonaEleitoral getZonaEleitoral(LocalVotacaoCSVDto localVotacaoCSVDto) {
		ZonaEleitoral zonaEleitoral;
		Optional<ZonaEleitoral> zonaEleitoralSalva = zonaEleitoralRepository
				.findByNumero(localVotacaoCSVDto.getNumeroZE());
		if (zonaEleitoralSalva.isEmpty()) {
			TRE tre = getTRE(localVotacaoCSVDto);
			zonaEleitoral = new ZonaEleitoral();
			Integer numeroZE = localVotacaoCSVDto.getNumeroZE();
			zonaEleitoral.setNome("Zona Eleitoral n?? " + numeroZE);
			zonaEleitoral.setNumero(numeroZE);
			zonaEleitoral.setTre(tre);
			zonaEleitoralRepository.save(zonaEleitoral);
		} else {
			zonaEleitoral = zonaEleitoralSalva.get();
		}
		return zonaEleitoral;
	}

	private TRE getTRE(LocalVotacaoCSVDto localVotacaoCSVDto) {
		TRE tre;
		Optional<TRE> treSalvo = treRepository.findByUf(localVotacaoCSVDto.getUf());
		if (treSalvo.isEmpty()) {
			tre = new TRE();
			tre.setUf(localVotacaoCSVDto.getUf());
			treRepository.save(tre);
		} else {
			tre = treSalvo.get();
		}
		return tre;
	}

	@EventListener
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			importarLocaisVotacao();
			importarCentrosDistribuicao();
			importarLocaisVotacaoTeste();
			importarCentrosDistribuicaoTeste();
			importarParametros();
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException("Erro ao importar os locais de votacao!");
		}
	}

	private void importarParametros() throws IllegalStateException, FileNotFoundException, IOException {
		List<ParametroCSVDto> parametrosCSV = new CsvToBeanBuilder(new FileReader(getArquivoParametroCSV()))
				.withType(ParametroCSVDto.class).withQuoteChar('\"').build().parse();
		log.info("Foram recuperados {} par??metros do CSV", parametrosCSV.size());
		for (ParametroCSVDto parametroCSVDto : parametrosCSV) {
			ParametroCalculo parametroCalculo = saveParametroFromCSV(parametroCSVDto);
			log.info("Salvando o Par??metro {}", parametroCalculo.getTipoParametro());
		}

	}

	private ParametroCalculo saveParametroFromCSV(ParametroCSVDto parametroCSVDto) {
		Optional<ParametroCalculo> tipoParametroSalvo = parametroCalculoRepository
				.findByTipoParametroEquals(parametroCSVDto.getTipoParametro());
		if (tipoParametroSalvo.isPresent()) {
			log.info("PAR??METRO DO TIPO {} J?? CADASTRADO!", parametroCSVDto.getTipoParametro());
			return tipoParametroSalvo.get();
		}
		log.info("Iniciando cadastro do par??metro {}", parametroCSVDto.getTipoParametro());
		ParametroCalculo parametroCalculo = montaParametro(parametroCSVDto);
		parametroCalculoRepository.save(parametroCalculo);
		return parametroCalculo;
	}

	private ParametroCalculo montaParametro(ParametroCSVDto parametroCSVDto) {
		ParametroCalculo parametro = new ParametroCalculo();
		parametro.setTipoParametro(parametroCSVDto.getTipoParametro());
		parametro.setValor(parametroCSVDto.getValor());
		return parametro;
	}

	private File getArquivoParametroCSV() throws IOException {
		return getArquivoDoClasspath("csv/parametros.csv");
	}

	private void importarCentrosDistribuicaoTeste() throws IllegalStateException, FileNotFoundException, IOException {
		List<CentroDistribuicaoCSVDto> centrosDistribuicaoCSV = new CsvToBeanBuilder(
				new FileReader(getArquivoCentrosDistribuicaoTesteCSV())).withType(CentroDistribuicaoCSVDto.class)
				.withQuoteChar('\"').build().parse();
		log.info("Foram recuperados {} centros de distribui????o do CSV", centrosDistribuicaoCSV.size());
		importarCentrosDistribuicao(centrosDistribuicaoCSV);
	}

	private void importarCentrosDistribuicao() throws IllegalStateException, FileNotFoundException, IOException {
		List<CentroDistribuicaoCSVDto> centrosDistribuicaoCSV = new CsvToBeanBuilder(
				new FileReader(getArquivoCentrosDistribuicaoCSV())).withType(CentroDistribuicaoCSVDto.class)
				.withQuoteChar('\"').build().parse();
		log.info("Foram recuperados {} centros de distribui????o do CSV", centrosDistribuicaoCSV.size());
		importarCentrosDistribuicao(centrosDistribuicaoCSV);
	}

	private void importarCentrosDistribuicao(List<CentroDistribuicaoCSVDto> centrosDistribuicaoCSV) {
		for (CentroDistribuicaoCSVDto centroDistribuicaoCSVDto : centrosDistribuicaoCSV) {
			CentroDistribuicao centroDistribuicao = saveCentroDistribuicaoFromCSV(centroDistribuicaoCSVDto);
			log.info("Salvando o Centro de Distribui????o {}", centroDistribuicao.getNome());
		}
	}

	private void importarLocaisVotacaoTeste() throws IllegalStateException, FileNotFoundException, IOException {
		List<LocalVotacaoCSVDto> locaisVotacaoCSV = new CsvToBeanBuilder(
				new FileReader(getArquivoLocaisVotacaoTesteCSV())).withType(LocalVotacaoCSVDto.class)
				.withQuoteChar('\"').build().parse();
		log.info("Foram recuperados {} registros do CSV", locaisVotacaoCSV.size());
		importarLocaisVotacao(locaisVotacaoCSV);
	}

	private void importarLocaisVotacao() throws IllegalStateException, FileNotFoundException, IOException {
		List<LocalVotacaoCSVDto> locaisVotacaoCSV = new CsvToBeanBuilder(new FileReader(getArquivoLocaisVotacaoCSV()))
				.withType(LocalVotacaoCSVDto.class).withQuoteChar('\"').build().parse();
		log.info("Foram recuperados {} registros do CSV", locaisVotacaoCSV.size());
		importarLocaisVotacao(locaisVotacaoCSV);
	}

	private void importarLocaisVotacao(List<LocalVotacaoCSVDto> locaisVotacaoCSV) {
		for (LocalVotacaoCSVDto localVotacaoCSVDto : locaisVotacaoCSV) {
			LocalVotacao localVotacao = saveLocalVotacaoFromCSV(localVotacaoCSVDto);
			log.info("Salvando a entidade {}, da {} Zona Eleitoral", localVotacao.getNome(),
					localVotacao.getZonaEleitoral().getNumero());
		}
	}

	private File getArquivoCentrosDistribuicaoTesteCSV() throws IOException {
		return getArquivoDoClasspath("csv/centro-distribuicao-teste.csv");
	}

	private File getArquivoCentrosDistribuicaoCSV() throws IOException {
		return getArquivoDoClasspath("csv/centros-distribuicao.csv");
	}

	private File getArquivoLocaisVotacaoCSV() throws IOException {
		return getArquivoDoClasspath("csv/locais-votacao-transporte.csv");
	}

	private File getArquivoLocaisVotacaoTesteCSV() throws IOException {
		return getArquivoDoClasspath("csv/locais-votacao-teste.csv");
	}

	private File getArquivoDoClasspath(String caminhoArquivo) throws IOException {
		return new ClassPathResource(caminhoArquivo).getFile();
	}

}
