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



    public static final class Static extends Friction {

        public Static(double maxValue) {
            super(maxValue, STATIC);
        }

        public Static(double normalForce, double coefficient) {
            this(normalForce * coefficient);
            type.setCoefficient(coefficient);
        }

        public BigDecimal getValue(double force) {
            if (value.compareTo(BigDecimal.valueOf(force)) < 0) {
                return value;
            }
            return BigDecimal.valueOf(force);
        }

        public BigDecimal getMaxValue() {
            return value;
        }
    }


    public static final class Dynamic extends Friction {

        public Dynamic(double value) {
            super(value, DYNAMIC);
        }

        public Dynamic(double normalForce, double coefficient) {
            this(normalForce * coefficient);
            type.setCoefficient(coefficient);
        }

        public BigDecimal getValue() {
            return value;
        }
    }
}
