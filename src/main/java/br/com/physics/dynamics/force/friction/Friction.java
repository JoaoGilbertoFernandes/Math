package br.com.physics.dynamics.force.friction;

import java.math.BigDecimal;

import static br.com.physics.dynamics.force.friction.FrictionType.*;

public abstract class Friction {

    final FrictionType type;
    final BigDecimal value;


    public Friction(double value, FrictionType type) {
        this.type = type;
        this.value = new BigDecimal(value);
    }

    public BigDecimal getCoefficient() {
        return type.getCoefficient();
    }

    public void setCoefficient(double coefficient) {
        type.setCoefficient(coefficient);
    }



    public static final class StaticFriction extends Friction {

        private final BigDecimal maxValue;

        public StaticFriction(double maxValue) {
            super(maxValue, STATIC);
            this.maxValue = BigDecimal.valueOf(maxValue);
        }

        public StaticFriction(double normalForce, double coefficient) {
            this(normalForce * coefficient);
            type.setCoefficient(coefficient);
        }

        public BigDecimal getValue(double force) {
            if (maxValue.compareTo(BigDecimal.valueOf(force)) < 0) {
                return maxValue;
            }
            return BigDecimal.valueOf(force);
        }

        public BigDecimal getMaxValue() {
            return maxValue;
        }
    }


    public static final class DynamicFriction extends Friction {

        public DynamicFriction(double value) {
            super(value, DYNAMIC);
        }

        public DynamicFriction(double normalForce, double coefficient) {
            this(normalForce * coefficient);
            type.setCoefficient(coefficient);
        }

        public BigDecimal getValue() {
            return value;
        }
    }
}
