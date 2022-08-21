package br.jus.tse.distribuicao_urnas.domain;

import br.jus.tse.distribuicao_urnas.model.TipoOtimizacaoEnum;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Simulacao {

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

    @Column(length = 1000)
    private String descricao;

    @Column
    private LocalDateTime dataHora;

    @Column
    @Enumerated(EnumType.STRING)
    private TipoOtimizacaoEnum tipoOtimizacao;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "veiculo_simulacao",
            joinColumns = @JoinColumn(name = "simulacao_id"),
            inverseJoinColumns = @JoinColumn(name = "veiculo_id")
    )
    private Set<Veiculo> veiculoSimulacaoVeiculos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "centro_distribuicao_id")
    private CentroDistribuicao centroDistribuicao;

    @OneToMany(mappedBy = "restricoes")
    private Set<RestricaoSimulacao> restricoesRestricaoSimulacaos;

}
