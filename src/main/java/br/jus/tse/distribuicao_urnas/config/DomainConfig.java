package br.jus.tse.distribuicao_urnas.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan({ "br.jus.tse.distribuicao_urnas.domain", "br.jus.tse.distribuicao_urnas.solver.domain" })
@EnableJpaRepositories("br.jus.tse.distribuicao_urnas.repos")
@EnableTransactionManagement
public class DomainConfig {
}
