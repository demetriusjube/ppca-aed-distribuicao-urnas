package br.jus.tse.distribuicao_urnas.solver.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DepotCustomers {

	private Depot depot;

	private List<Customer> customerList = new ArrayList<Customer>();

}
