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
public class LocalVotacao {

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
    private Integer numero;

    @Column
    private String nome;

    @Column
    private String endereco;

    @Column
    private Integer quantidadeSecoes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zona_eleitoral_id")
    private ZonaEleitoral zonaEleitoral;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "localizacao_id")
    private Localizacao localizacao;

    @OneToMany(mappedBy = "localVotacao")
    private Set<Visita> localVotacaoVisitas;

}
