package org.javaboost.inheritance;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ManagerEmployee extends SimpleEmployee {

    private List<Employee> employees;

    public ManagerEmployee(String name, int age) {
        super(name, age);
        employees = new ArrayList<>();
    }

    public ManagerEmployee(String name, int age, List<Employee> employees) {
        super(name, age);
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public BigDecimal calculateBonus() {
        Duration duration = calculateServiceTime(LocalDateTime.now());
        long workMonths = duration.toDays() / 21;
        return BigDecimal.valueOf(100).multiply(BigDecimal.valueOf(workMonths));
    }
}
