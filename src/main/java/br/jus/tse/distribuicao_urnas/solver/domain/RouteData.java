package br.jus.tse.distribuicao_urnas.solver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RouteData {

	private Long distanceMeters;

	private Long drivingTimeMilis;

}
