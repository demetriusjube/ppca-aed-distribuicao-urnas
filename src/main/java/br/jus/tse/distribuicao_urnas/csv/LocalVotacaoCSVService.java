package br.jus.tse.distribuicao_urnas.csv;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.tse.distribuicao_urnas.domain.LocalVotacao;
import br.jus.tse.distribuicao_urnas.domain.Localizacao;
import br.jus.tse.distribuicao_urnas.domain.TRE;
import br.jus.tse.distribuicao_urnas.domain.ZonaEleitoral;
import br.jus.tse.distribuicao_urnas.repos.LocalVotacaoRepository;
import br.jus.tse.distribuicao_urnas.repos.LocalizacaoRepository;
import br.jus.tse.distribuicao_urnas.repos.TRERepository;
import br.jus.tse.distribuicao_urnas.repos.ZonaEleitoralRepository;

@Service
public class LocalVotacaoCSVService {

	@Autowired
	private LocalVotacaoRepository localVotacaoRepository;

	@Autowired
	private ZonaEleitoralRepository zonaEleitoralRepository;

	@Autowired
	private LocalizacaoRepository localizacaoRepository;

	@Autowired
	private TRERepository treRepository;

	@Transactional
	public LocalVotacao saveLocalVotacaoFromCSV(LocalVotacaoCSVDto localVotacaoCSVDto) {

		Optional<LocalVotacao> localVotacaoSalvo = localVotacaoRepository
				.findByNumero(localVotacaoCSVDto.getNumeroLocalVotacao());
		if (localVotacaoSalvo.isPresent()) {
			return localVotacaoSalvo.get();
		}
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
}
