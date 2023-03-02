package org.javaboost.inheritance;

import java.math.BigDecimal;

public class SeasonEmployee extends Employee {

    public SeasonEmployee(String name, int age) {
        super(name, age);
    }

    @Override
    public BigDecimal calculateSalary() {
        return BigDecimal.valueOf(1000);
    }

    @Override
    public BigDecimal calculateBonus() {
        return BigDecimal.valueOf(50);
    }
}
