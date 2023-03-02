package org.javaboost.inheritance;

import java.math.BigDecimal;

public class SimpleEmployee extends Employee implements InsuranceEligible {

    public SimpleEmployee(String name, int age) {
        super(name, age);
    }

    @Override
    public BigDecimal calculateBonus() {
        return BigDecimal.valueOf(0);
    }

    @Override
    public BigDecimal calculateInsurance() {
        return getAge() > 21 ?
                BigDecimal.valueOf(10).add(calculateDefaultInsurance())
                : BigDecimal.valueOf(20).add(calculateDefaultInsurance());
    }
}
