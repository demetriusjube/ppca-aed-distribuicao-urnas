package br.jus.tse.distribuicao_urnas.csv;

import java.math.BigDecimal;

import com.opencsv.bean.CsvBindByName;

import lombok.Data;

@Data
public class LocalVotacaoCSVDto {

	@CsvBindByName
	private String uf;
	@CsvBindByName
	private Long numeroZE;
	@CsvBindByName
	private Long numeroLocalVotacao;
	@CsvBindByName
	private String nome;
	@CsvBindByName
	private String endereco;
	@CsvBindByName
	private Integer quantidadeSecoes;
	@CsvBindByName
	private BigDecimal latitude;
	@CsvBindByName
	private BigDecimal longitude;

}
