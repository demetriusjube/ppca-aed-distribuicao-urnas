package br.jus.tse.distribuicao_urnas.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
import br.jus.tse.distribuicao_urnas.domain.TRE;
import br.jus.tse.distribuicao_urnas.domain.ZonaEleitoral;
import br.jus.tse.distribuicao_urnas.repos.CentroDistribuicaoRepository;
import br.jus.tse.distribuicao_urnas.repos.LocalVotacaoRepository;
import br.jus.tse.distribuicao_urnas.repos.LocalizacaoRepository;
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

	@Transactional
	public CentroDistribuicao saveCentroDistribuicaoFromCSV(CentroDistribuicaoCSVDto centroDistribuicaoCSVDto) {

		Optional<CentroDistribuicao> centroDistribuicaoSalvo = centroDistribuicaoRepository
				.findByEnderecoEquals(centroDistribuicaoCSVDto.getEndereco());
		if (centroDistribuicaoSalvo.isPresent()) {
			log.info("CENTRO DE DISTRIBUIÇÃO {} JÁ CADASTRADO!", centroDistribuicaoCSVDto.getNome());
			return centroDistribuicaoSalvo.get();
		}
		log.info("Iniciando cadastro do Centro de distribuição ");
		CentroDistribuicao centroDistribuicao = montaCentroDistribuicao(centroDistribuicaoCSVDto);
		centroDistribuicaoRepository.save(centroDistribuicao);
		return centroDistribuicao;

	}

	private CentroDistribuicao montaCentroDistribuicao(CentroDistribuicaoCSVDto centroDistribuicaoCSVDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public LocalVotacao saveLocalVotacaoFromCSV(LocalVotacaoCSVDto localVotacaoCSVDto) {

		Optional<LocalVotacao> localVotacaoSalvo = localVotacaoRepository
				.findByNumeroEqualsAndZonaEleitoralNumeroEquals(localVotacaoCSVDto.getNumeroLocalVotacao(),
						localVotacaoCSVDto.getNumeroZE());
		if (localVotacaoSalvo.isPresent()) {
			log.info("LOCAL DE VOTAÇÃO Nº {} {} JÁ CADASTRADO!", localVotacaoCSVDto.getNumeroLocalVotacao(),
					localVotacaoCSVDto.getNome());
			return localVotacaoSalvo.get();
		}
		log.info("Iniciando cadastro do local de votação Nº {} {} ");
		LocalVotacao localVotacao = montaLocalVotacao(localVotacaoCSVDto);
		localVotacaoRepository.save(localVotacao);
		return localVotacao;

	}

	private LocalVotacao montaLocalVotacao(LocalVotacaoCSVDto localVotacaoCSVDto) {
		ZonaEleitoral zonaEleitoral = getZonaEleitoral(localVotacaoCSVDto);
		Localizacao localizacao = getLocalizacao(localVotacaoCSVDto);
		LocalVotacao localVotacao = new LocalVotacao();
		localVotacao.setEndereco(localVotacaoCSVDto.getEndereco());
		localVotacao.setLocalizacao(localizacao);
		localVotacao.setNome(localVotacaoCSVDto.getNome());
		localVotacao.setNumero(localVotacaoCSVDto.getNumeroLocalVotacao().longValue());
		localVotacao.setQuantidadeSecoes(localVotacaoCSVDto.getQuantidadeSecoes());
		localVotacao.setZonaEleitoral(zonaEleitoral);
		return localVotacao;
	}

	private Localizacao getLocalizacao(LocalVotacaoCSVDto localVotacaoCSVDto) {
		Localizacao localizacao;

		Optional<Localizacao> localizacaoSalva = localizacaoRepository.findByLatitudeEqualsAndLongitudeEquals(
				localVotacaoCSVDto.getLatitude(), localVotacaoCSVDto.getLongitude());
		if (localizacaoSalva.isEmpty()) {
			localizacao = new Localizacao();
			localizacao.setLatitude(localVotacaoCSVDto.getLatitude());
			localizacao.setLongitude(localVotacaoCSVDto.getLongitude());
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
			Long numeroZE = localVotacaoCSVDto.getNumeroZE();
			zonaEleitoral.setNome("Zona Eleitoral nº " + numeroZE);
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
			tre.setNumero(1l);
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
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException("Erro ao importar os locais de votacao!");
		}
	}

	private void importarCentrosDistribuicao() throws IllegalStateException, FileNotFoundException, IOException {
		List<CentroDistribuicaoCSVDto> centrosDistribuicaoCSV = new CsvToBeanBuilder(
				new FileReader(getArquivoCentrosDistribuicaoCSV())).withType(CentroDistribuicaoCSVDto.class)
				.withQuoteChar('\"').build().parse();
		log.info("Foram recuperados {} centros de distribuição do CSV", centrosDistribuicaoCSV.size());
		for (CentroDistribuicaoCSVDto centroDistribuicaoCSVDto : centrosDistribuicaoCSV) {
			CentroDistribuicao centroDistribuicao = saveCentroDistribuicaoFromCSV(centroDistribuicaoCSVDto);
			log.info("Salvando o Centro de Distribuição {}", centroDistribuicao.getNome());
		}
	}

	private void importarLocaisVotacao() throws IllegalStateException, FileNotFoundException, IOException {
		List<LocalVotacaoCSVDto> locaisVotacaoCSV = new CsvToBeanBuilder(new FileReader(getArquivoLocaisVotacaoCSV()))
				.withType(LocalVotacaoCSVDto.class).withQuoteChar('\"').build().parse();
		log.info("Foram recuperados {} registros do CSV", locaisVotacaoCSV.size());
		for (LocalVotacaoCSVDto localVotacaoCSVDto : locaisVotacaoCSV) {
			LocalVotacao localVotacao = saveLocalVotacaoFromCSV(localVotacaoCSVDto);
			log.info("Salvando a entidade {}, da {} Zona Eleitoral", localVotacao.getNome(),
					localVotacao.getZonaEleitoral().getNumero());
		}
	}

	private File getArquivoCentrosDistribuicaoCSV() throws IOException {
		return getArquivoDoClasspath("csv/centros-distribuicao.csv");
	}

	private File getArquivoLocaisVotacaoCSV() throws IOException {
		return getArquivoDoClasspath("csv/locais-votacao-transporte.csv");
	}

	private File getArquivoDoClasspath(String caminhoArquivo) throws IOException {
		return new ClassPathResource(caminhoArquivo).getFile();
	}

}
