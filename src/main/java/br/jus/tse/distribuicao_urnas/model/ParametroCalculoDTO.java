package br.jus.tse.distribuicao_urnas.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ParametroCalculoDTO {

    private Long id;
    private Double valor;
    private TipoParametroEnum tipoParametro;

}
