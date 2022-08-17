package br.jus.tse.distribuicao_urnas.domain;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Localizacao {

	@Id
	@Column(nullable = false, updatable = false)
	@SequenceGenerator(name = "primary_sequence", sequenceName = "primary_sequence", allocationSize = 1, initialValue = 10000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "primary_sequence")
	private Long id;

	@Column(nullable = false, precision = 9, scale = 7)
	private BigDecimal latitude;

	@Column(precision = 9, scale = 7)
	private BigDecimal longitude;

	@OneToMany(mappedBy = "localizacao")
	private Set<LocalVotacao> localizacaoLocalVotacaos;

	@OneToMany(mappedBy = "origem")
	private Set<Distancia> origemDistancias;

	@OneToMany(mappedBy = "destino")
	private Set<Distancia> destinoDistancias;

	@OneToMany(mappedBy = "localizacao")
	private Set<Visita> localizacaoVisitas;

	@OneToMany(mappedBy = "localizacao")
	private Set<CentroDistribuicao> localizacaoCentroDistribuicaos;

	/**
	 * Create coordinates with the given latitude and longitude.
	 *
	 * @param latitude  latitude
	 * @param longitude longitude
	 * @return coordinates with the given latitude and longitude
	 */
	@Transient
	public static Localizacao of(double latitude, double longitude) {
		Localizacao localizacao = new Localizacao();
		localizacao.setLatitude(BigDecimal.valueOf(latitude));
		localizacao.setLongitude(BigDecimal.valueOf(longitude));
		return localizacao;
	}

}
