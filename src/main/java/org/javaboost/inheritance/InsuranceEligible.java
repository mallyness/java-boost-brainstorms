package org.javaboost.inheritance;

import java.math.BigDecimal;

public interface InsuranceEligible {

    BigDecimal calculateInsurance();

    default BigDecimal calculateDefaultInsurance() {
        return BigDecimal.valueOf(12);
    }
}
