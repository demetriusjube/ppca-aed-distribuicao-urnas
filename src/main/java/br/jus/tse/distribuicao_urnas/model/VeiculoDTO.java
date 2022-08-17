package br.jus.tse.distribuicao_urnas.model;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VeiculoDTO {

    private Long id;

    @Size(max = 255)
    private String descricao;

    @Size(max = 10)
    private String placa;

    private Integer capacidade;

}
