package br.jus.tse.distribuicao_urnas.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LocalVotacaoDTO {

    private Long id;

    @NotNull
    private Integer numero;

    @Size(max = 255)
    private String nome;

    @Size(max = 255)
    private String endereco;

    private Integer quantidadeSecoes;

    private Long zonaEleitoral;

    private Long localizacao;

}
