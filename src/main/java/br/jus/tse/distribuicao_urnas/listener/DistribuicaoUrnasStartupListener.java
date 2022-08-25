package br.jus.tse.distribuicao_urnas.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.tse.distribuicao_urnas.model.TipoOtimizacaoEnum;
import br.jus.tse.distribuicao_urnas.solver.builder.DistribuicaoUrnasSolutionBuilder;
import br.jus.tse.distribuicao_urnas.solver.domain.VehicleRoutingSolution;
import br.jus.tse.distribuicao_urnas.solver.persistence.VehicleRoutingSolutionRepository;

@Service
public class DistribuicaoUrnasStartupListener {

	@Autowired
	private VehicleRoutingSolutionRepository vehicleRoutingSolutionRepository;

	@Autowired
	private DistribuicaoUrnasSolutionBuilder distribuicaoUrnasSolutionBuilder;

//	@EventListener
//	@Transactional
//	public void onApplicationEvent(ContextRefreshedEvent event) {
//		VehicleRoutingSolution problem = distribuicaoUrnasSolutionBuilder.build(11240l, 18, 400,
//				TipoOtimizacaoEnum.MENOR_DISTANCIA);
//		vehicleRoutingSolutionRepository.update(problem);
//	}

}
