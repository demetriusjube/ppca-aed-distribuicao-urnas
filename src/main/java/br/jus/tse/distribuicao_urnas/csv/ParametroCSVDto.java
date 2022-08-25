package br.jus.tse.distribuicao_urnas.csv;

import com.opencsv.bean.CsvBindByName;

import br.jus.tse.distribuicao_urnas.model.TipoParametroEnum;
import lombok.Data;

@Data
public class ParametroCSVDto {

	@CsvBindByName
	private TipoParametroEnum tipoParametro;
	@CsvBindByName
	private Double valor;

}
