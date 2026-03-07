package br.com.math.Function;

import java.util.function.Function;

public interface DifferentiableFunction extends Function<Double, Double> {

    boolean isZeroFunction();

    DifferentiableFunction derivative();

    default DifferentiableFunction derivative(int order) {
        if (order == 0) return this;
        DifferentiableFunction result = this;
        for (int i = 0; i < order; i++) {
            result = result.derivative();
        }
        return result;
    }
}
