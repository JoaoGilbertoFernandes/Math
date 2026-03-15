package br.com.physics.dynamics.force;

import java.util.function.Function;

public class Force1D {

    private double value;

    private final Function<Double, Double> function;

    public Force1D(double value) {
        this.value = value;
        function = x -> value;
    }

    public Force1D(double mass, double acceleration) {
        this(mass * acceleration);
    }

    public Force1D(Function<Double, Double> function) {
        this.value = function.apply(0.0);
        this.function = function;
    }


    public double valueAt(double param) {
        value = function.apply(param);
        return value;
    }

    public double getValue() {
        return value;
    }

    public Function<Double, Double> getFunction() {
        return function;
    }
}
