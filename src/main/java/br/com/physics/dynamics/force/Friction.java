package br.com.physics.dynamics.force;

public class Friction extends Force1D {

    private final FrictionType type;

    public Friction(double value, FrictionType type) {
        super(value);
        this.type = type;
    }

    public Friction(Force1D normalForce, FrictionType type, double coefficient) {
        super(x -> coefficient * normalForce.getFunction().apply(x));
        this.type = type;
    }


    public double getCoefficient(Force1D normalForce) {
        if (normalForce.getValue() == 0.0) {
            throw new IllegalArgumentException("Normal Force must have non zero value.");
        }
        return getValue() / normalForce.getValue();
    }

    public FrictionType getType() {
        return type;
    }
}
