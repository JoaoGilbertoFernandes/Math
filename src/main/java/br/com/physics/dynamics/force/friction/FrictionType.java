package br.com.physics.dynamics.force.friction;

import java.math.BigDecimal;

public enum FrictionType {
    STATIC(0.0),
    DYNAMIC(0.0);

    private BigDecimal coefficient;

    FrictionType(double coefficient) {
        this.coefficient = new BigDecimal(coefficient);
    }

    public BigDecimal getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = new BigDecimal(coefficient);
    }
}
