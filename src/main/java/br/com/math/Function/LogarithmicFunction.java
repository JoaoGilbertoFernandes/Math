package br.com.math.function;

public class LogarithmicFunction implements DifferentiableFunction {

    private final double base;
    private final double coefficient;

    public LogarithmicFunction() {
        base = Math.E;
        coefficient = 1.0;
    }

    public LogarithmicFunction(double base, double coefficient) {
        validateBase(base);
        this.base = base;
        this.coefficient = coefficient;
    }

    public boolean isZeroFunction() {
        return coefficient == 0.0;
    }

    @Override
    public Double apply(Double x) {
        return coefficient * Math.log(x) / Math.log(base);
    }

    public ReciprocalFunction derivative() {
        return new ReciprocalFunction(1, 1 / Math.log(base));
    }


    private static void validateBase(double value) {
        if (value == 1.0 || value < 0.0)
            throw new IllegalArgumentException("Base must be positive and different than 1");
    }
}
