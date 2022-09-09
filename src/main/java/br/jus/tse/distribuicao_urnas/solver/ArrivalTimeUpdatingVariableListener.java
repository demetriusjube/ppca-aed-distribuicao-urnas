package br.jus.tse.distribuicao_urnas.solver;

import java.util.Objects;

import org.optaplanner.core.api.domain.variable.VariableListener;
import org.optaplanner.core.api.score.director.ScoreDirector;

import br.jus.tse.distribuicao_urnas.solver.domain.Customer;
import br.jus.tse.distribuicao_urnas.solver.domain.Depot;
import br.jus.tse.distribuicao_urnas.solver.domain.Standstill;
import br.jus.tse.distribuicao_urnas.solver.domain.Vehicle;
import br.jus.tse.distribuicao_urnas.solver.domain.VehicleRoutingSolution;

// TODO When this class is added only for TimeWindowedCustomer, use TimeWindowedCustomer instead of Customer
public class ArrivalTimeUpdatingVariableListener implements VariableListener<VehicleRoutingSolution, Customer> {

    @Override
    public void beforeEntityAdded(ScoreDirector<VehicleRoutingSolution> scoreDirector, Customer customer) {
        // Do nothing
    }

    @Override
    public void afterEntityAdded(ScoreDirector<VehicleRoutingSolution> scoreDirector, Customer customer) {
            updateArrivalTime(scoreDirector, customer);
    }

    @Override
    public void beforeVariableChanged(ScoreDirector<VehicleRoutingSolution> scoreDirector, Customer customer) {
        // Do nothing
    }

    @Override
    public void afterVariableChanged(ScoreDirector<VehicleRoutingSolution> scoreDirector, Customer customer) {
            updateArrivalTime(scoreDirector, customer);
    }

    @Override
    public void beforeEntityRemoved(ScoreDirector<VehicleRoutingSolution> scoreDirector, Customer customer) {
        // Do nothing
    }

    @Override
    public void afterEntityRemoved(ScoreDirector<VehicleRoutingSolution> scoreDirector, Customer customer) {
        // Do nothing
    }

    protected void updateArrivalTime(ScoreDirector<VehicleRoutingSolution> scoreDirector,
            Customer sourceCustomer) {
        Standstill previousStandstill = sourceCustomer.getPreviousStandstill();
        Long departureTime = previousStandstill == null ? null
                : (previousStandstill instanceof Customer)
                        ? ((Customer) previousStandstill).getDepartureTime()
                        : ((Depot) ((Vehicle) previousStandstill).getDepot()).getReadyTime();
        Customer shadowCustomer = sourceCustomer;
        Long arrivalTime = calculateArrivalTime(shadowCustomer, departureTime);
        while (shadowCustomer != null && !Objects.equals(shadowCustomer.getArrivalTime(), arrivalTime)) {
            scoreDirector.beforeVariableChanged(shadowCustomer, "arrivalTime");
            shadowCustomer.setArrivalTime(arrivalTime);
            scoreDirector.afterVariableChanged(shadowCustomer, "arrivalTime");
            departureTime = shadowCustomer.getDepartureTime();
            shadowCustomer = shadowCustomer.getNextVisit();
            arrivalTime = calculateArrivalTime(shadowCustomer, departureTime);
        }
    }

    private Long calculateArrivalTime(Customer customer, Long previousDepartureTime) {
        if (customer == null || customer.getPreviousStandstill() == null) {
            return null;
        }
        if (customer.getPreviousStandstill() instanceof Vehicle) {
            // PreviousStandstill is the Vehicle, so we leave from the Depot at the best suitable time
            return Math.max(customer.getReadyTime(),
                    previousDepartureTime + customer.getDistanceFromPreviousStandstill());
        }
        return previousDepartureTime + customer.getDistanceFromPreviousStandstill();
    }

}
