package br.com.physics.dynamics.force;

import java.util.function.Function;

public class Weight extends Force1D {

    private final double mass;

    private final Function<Double, Double> gravity;

    public static final double EARTH_GRAVITY = 9.80665;

    public Weight(double value) {
        super(value);
        mass = value / EARTH_GRAVITY;
        gravity = x -> EARTH_GRAVITY;
    }

    public Weight(double mass, Function<Double, Double> gravity) {
        super(x -> mass * gravity.apply(x));
        this.mass = mass;
        this.gravity = gravity;
    }

    public Weight(double mass, double gravity) {
        this(mass, x -> gravity);
    }


    public double getMass() {
        return mass;
    }

    public Function<Double, Double> getGravity() {
        return gravity;
    }
}
