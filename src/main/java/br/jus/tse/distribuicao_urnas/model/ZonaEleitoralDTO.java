package br.jus.tse.distribuicao_urnas.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ZonaEleitoralDTO {

    private Long id;

    @NotNull
    private Integer numero;

    @Size(max = 300)
    private String nome;

    private Long tre;

    private Long centroDistribuicao;

}
