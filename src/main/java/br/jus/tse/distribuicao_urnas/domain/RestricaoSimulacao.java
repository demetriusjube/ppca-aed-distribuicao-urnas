package br.jus.tse.distribuicao_urnas.domain;

import br.jus.tse.distribuicao_urnas.model.TipoRestricaoEnum;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class RestricaoSimulacao {

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
    private Double valor;

    @Column
    @Enumerated(EnumType.STRING)
    private TipoRestricaoEnum tipoRestricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restricoes_id")
    private Simulacao restricoes;

}
