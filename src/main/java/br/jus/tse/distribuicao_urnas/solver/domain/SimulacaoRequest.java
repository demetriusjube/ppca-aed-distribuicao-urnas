package br.jus.tse.distribuicao_urnas.solver.domain;

import br.jus.tse.distribuicao_urnas.model.TipoOtimizacaoEnum;
import lombok.Data;

@Data
public class SimulacaoRequest {

	private Long idCentroDistribuicao;

	private Integer quantidadeCaminhoes38m3;

	private Integer quantidadeCaminhoes22m3;

	private Integer quantidadeCaminhoes13m3;

	private Integer quantidadeCaminhoes7_5m3;

	private TipoOtimizacaoEnum tipoOtimizacaoEnum;

	private Integer tempoDescarregamentoMinutos;

	private Integer tempoMaximoAtuacaoHoras;

}
