package br.jus.tse.distribuicao_urnas.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DistanciaDTO {

    private Long id;
    private Long menorTempoViagem;
    private Long menorDistancia;
    private Long origem;
    private Long destino;

}
