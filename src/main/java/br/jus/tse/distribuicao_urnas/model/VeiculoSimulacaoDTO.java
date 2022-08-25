package br.jus.tse.distribuicao_urnas.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VeiculoSimulacaoDTO {

    private Long id;
    private Integer urnasTransportadas;
    private Long simulacao;
    private Long veiculo;
    private Long planoRota;

}
