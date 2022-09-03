package br.jus.tse.distribuicao_urnas.solver.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.InverseRelationShadowVariable;

@PlanningEntity
public interface Standstill {

    /**
     * The standstill's location.
     *
     * @return never {@code null}
     */
    Location getLocation();

    /**
     * The next visit after this standstill.
     *
     * @return sometimes {@code null}
     */
    @InverseRelationShadowVariable(sourceVariableName = "previousStandstill")
    Customer getNextVisit();

    void setNextVisit(Customer nextVisit);
}
