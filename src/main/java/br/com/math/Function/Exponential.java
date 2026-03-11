package br.com.math.function;

public class Exponential implements Differentiable {

    private final double base;
    private final double coefficient;

    public Exponential() {
        base = Math.E;
        coefficient = 1.0;
    }

    public Exponential(double base, double coefficient) {
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

    public Exponential derivative() {
        return new Exponential(base, Math.log(base) * coefficient);
    }
}
