package br.jus.tse.distribuicao_urnas.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class ZonaEleitoral {

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

    @Column(nullable = false)
    private Long numero;

    @Column(length = 300)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tre_id")
    private TRE tre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "centro_distribuicao_id")
    private CentroDistribuicao centroDistribuicao;

    @OneToMany(mappedBy = "zonaEleitoral")
    private Set<LocalVotacao> zonaEleitoralLocalVotacaos;

}
