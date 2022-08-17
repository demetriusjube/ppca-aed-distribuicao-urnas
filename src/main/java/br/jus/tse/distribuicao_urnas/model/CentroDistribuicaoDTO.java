package br.jus.tse.distribuicao_urnas.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CentroDistribuicaoDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String nome;

    @Size(max = 500)
    private String endereco;

    private Long localizacao;

}
