package br.jus.tse.distribuicao_urnas.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Veiculo {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long id;

    @Column
    private String descricao;

    @Column(length = 10)
    private String placa;

    @Column
    private Integer capacidade;

    @OneToMany(mappedBy = "veiculo")
    private Set<PlanoRota> veiculoPlanoRotas;

    @ManyToMany(mappedBy = "veiculoSimulacaoVeiculos")
    private Set<Simulacao> veiculoSimulacaoSimulacaos;

}
