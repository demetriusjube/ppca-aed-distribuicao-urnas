package br.jus.tse.distribuicao_urnas.csv;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.jus.tse.distribuicao_urnas.service.LocalVotacaoService;

@SpringBootTest
public class LocalVotacaoImporter {
	
	@Autowired
	private LocalVotacaoService localVotacaoService;

	@Test
	public void importarLocaisVotacao() {
		
	}
}
