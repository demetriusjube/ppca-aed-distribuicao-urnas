package br.jus.tse.distribuicao_urnas.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TREDTO {

    private Long id;

    @NotNull
    @Size(max = 2)
    private String uf;

}
