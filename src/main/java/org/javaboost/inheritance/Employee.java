package org.javaboost.inheritance;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public abstract class Employee extends Person {

    private int id;
    private LocalDateTime hireDate;
    private BigDecimal salary;
    private Employee manager;

    public Employee(String name, int age) {
        super(name, age);
    }

    public Duration calculateServiceTime(LocalDateTime offDate) {
        return Duration.between(hireDate, offDate);
    }

    public BigDecimal calculateSalary() {
        // some logic
        return BigDecimal.valueOf(1_000).add(calculateBonus());
    }

    public BigDecimal calculateBonus() {
        Duration duration = calculateServiceTime(LocalDateTime.now());
        long workMonths = duration.toDays() / 21;
        return BigDecimal.valueOf(100).multiply(BigDecimal.valueOf(workMonths));
    }
}
