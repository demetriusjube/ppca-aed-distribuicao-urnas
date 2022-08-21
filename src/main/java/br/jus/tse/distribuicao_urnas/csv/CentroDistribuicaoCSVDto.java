package br.jus.tse.distribuicao_urnas.csv;

import java.math.BigDecimal;
import java.util.List;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;

import lombok.Data;

@Data
public class CentroDistribuicaoCSVDto {

	@CsvBindByName
	private String nome;
	@CsvBindByName
	private String endereco;
	@CsvBindAndSplitByName(elementType = Long.class, splitOn = ",")
	private List<Integer> zonasEleitorais;
	@CsvBindByName
	private BigDecimal latitude;
	@CsvBindByName
	private BigDecimal longitude;

}
