package br.jus.tse.distribuicao_urnas.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RestricaoSimulacaoDTO {

    private Long id;
    private Double valor;
    private TipoRestricaoEnum tipoRestricao;
    private Long restricoes;

}
