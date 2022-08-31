package br.jus.tse.distribuicao_urnas.csv;

import com.opencsv.bean.CsvBindByName;

import lombok.Data;

@Data
public class RotaExistenteCSVDto {

	@CsvBindByName
	private Integer numeroZE;
	@CsvBindByName
	private Integer numeroLocalVotacao;
	@CsvBindByName
	private Integer parada;
	@CsvBindByName
	private Integer rota;

}
