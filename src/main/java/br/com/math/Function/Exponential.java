package br.com.math.function;

public class Exponential implements Differentiable {

    private final double base;
    private final double amplitude;
    private final double growthRate;

    public Exponential() {
        base = Math.E;
        amplitude = 1.0;
        growthRate = 1.0;
    }

    public Exponential(double base, double amplitude, double growthRate) {
        this.base = base;
        this.amplitude = amplitude;
        this.growthRate = growthRate;
    }

    public boolean isZeroFunction() {
        return amplitude == 0.0;
    }

    @Override
    public Double apply(Double x) {
        return amplitude * Math.pow(base, growthRate * x);
    }

    public Exponential derivative() {
        return new Exponential(base, (amplitude * growthRate * Math.log(base)), growthRate);
    }

    public Exponential integral() {
        return new Exponential(base, (amplitude / (growthRate * Math.log(base))), growthRate);
    }

    @Override
    public String toString() {
        return String.format("%.2f·%s^(%.2f·x)", amplitude, base, growthRate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exponential e)) return false;
        return Double.compare(base, e.base) == 0 &&
                Double.compare(amplitude, e.amplitude) == 0 &&
                Double.compare(growthRate, e.growthRate) == 0;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(base, amplitude, growthRate);
    }


}
