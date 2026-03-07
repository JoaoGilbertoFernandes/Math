package br.com.math.function;

public class ExponentialFunction implements DifferentiableFunction {

    private final double base;
    private final double coefficient;

    public ExponentialFunction() {
        base = Math.E;
        coefficient = 1.0;
    }

    public ExponentialFunction(double base, double coefficient) {
        this.base = base;
        this.coefficient = coefficient;
    }

    public boolean isZeroFunction() {
        return coefficient == 0.0;
    }

    @Override
    public Double apply(Double x) {
        return Math.pow(base, x) * coefficient;
    }

    public ExponentialFunction derivative() {
        return new ExponentialFunction(base, Math.log(base) * coefficient);
    }
}
