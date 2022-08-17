package br.jus.tse.distribuicao_urnas.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


@Getter
@Setter
public class SimulacaoDTO {

    private Long id;

    @Size(max = 1000)
    private String descricao;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHora;

    private List<Long> veiculoSimulacaos;

    private Long centroDistribuicao;

}
