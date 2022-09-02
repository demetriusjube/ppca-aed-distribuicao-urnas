package br.jus.tse.distribuicao_urnas.solver.builder;

import br.jus.tse.distribuicao_urnas.domain.CentroDistribuicao;
import br.jus.tse.distribuicao_urnas.domain.LocalVotacao;
import br.jus.tse.distribuicao_urnas.solver.domain.Location;

public class LocationBuilder {

	public static Location buildFrom(CentroDistribuicao centroDistribuicao) {
		return new Location(centroDistribuicao.getLocalizacao().getId(),
				centroDistribuicao.getLocalizacao().getLatitude(), centroDistribuicao.getLocalizacao().getLongitude(),
				centroDistribuicao.getEndereco(), centroDistribuicao.getNome());
	}

	public static Location buildFrom(LocalVotacao localVotacao) {
		return new Location(localVotacao.getLocalizacao().getId(), localVotacao.getLocalizacao().getLatitude(),
				localVotacao.getLocalizacao().getLongitude(), localVotacao.getEndereco(), localVotacao.getNome());
	}

}
