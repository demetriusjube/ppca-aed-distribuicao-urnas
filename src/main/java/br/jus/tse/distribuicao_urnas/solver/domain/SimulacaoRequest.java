package br.jus.tse.distribuicao_urnas.solver.domain;

import java.util.ArrayList;
import java.util.List;

import br.jus.tse.distribuicao_urnas.model.TipoOtimizacaoEnum;
import lombok.Data;

@Data
public class SimulacaoRequest {

	private Long idCentroDistribuicao;

	private List<VehicleRequest> veiculos = new ArrayList<VehicleRequest>();

	private TipoOtimizacaoEnum tipoOtimizacaoEnum;

	private Integer tempoDescarregamentoMinutos;

	private Integer tempoMaximoAtuacaoHoras;

}
