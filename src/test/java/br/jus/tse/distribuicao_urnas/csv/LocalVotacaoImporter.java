package br.jus.tse.distribuicao_urnas.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.opencsv.bean.CsvToBeanBuilder;

@SpringBootTest
@Transactional
public class LocalVotacaoImporter {

	@Autowired
	private LocalVotacaoCSVService localVotacaoCSVService;

	@Test
	@Rollback(false)
	public void importarLocaisVotacao() throws IllegalStateException, FileNotFoundException, IOException {
		List<LocalVotacaoCSVDto> locaisVotacaoCSV = new CsvToBeanBuilder(new FileReader(getArquivoCSV()))
				.withType(LocalVotacaoCSVDto.class).withQuoteChar('\"').build().parse();
		for (LocalVotacaoCSVDto localVotacaoCSVDto : locaisVotacaoCSV) {
			localVotacaoCSVService.saveLocalVotacaoFromCSV(localVotacaoCSVDto);
		}
	}

	private File getArquivoCSV() throws IOException {
		return new ClassPathResource("csv/locais-votacao-transporte.csv").getFile();
	}
}
