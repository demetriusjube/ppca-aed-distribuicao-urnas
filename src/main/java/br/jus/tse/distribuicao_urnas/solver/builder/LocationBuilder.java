package br.jus.tse.distribuicao_urnas.solver.builder;

import br.jus.tse.distribuicao_urnas.domain.CentroDistribuicao;
import br.jus.tse.distribuicao_urnas.domain.LocalVotacao;
import br.jus.tse.distribuicao_urnas.solver.domain.Location;

public class LocationBuilder {

	public static Location buildFrom(CentroDistribuicao centroDistribuicao) {
		return new Location(centroDistribuicao.getId(), centroDistribuicao.getLocalizacao().getLatitude(),
				centroDistribuicao.getLocalizacao().getLongitude());
	}

	public static Location buildFrom(LocalVotacao localVotacao) {
		return new Location(localVotacao.getId(), localVotacao.getLocalizacao().getLatitude(),
				localVotacao.getLocalizacao().getLongitude());
	}

}
