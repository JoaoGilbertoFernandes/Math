package br.com.math.function;

public class Logarithmic implements Differentiable {

    private final double base;
    private final double amplitude;
    private final double growthRate;

    public Logarithmic() {
        base = Math.E;
        amplitude = 1.0;
        growthRate = 1.0;
    }

    public Logarithmic(double base, double amplitude, double growthRate) {
        validateBase(base);
        this.base = base;
        this.amplitude = amplitude;
        this.growthRate = growthRate;
    }

    public boolean isZeroFunction() {
        return amplitude == 0.0;
    }

    @Override
    public Double apply(Double x) {
        return (amplitude * Math.log(growthRate * x)) / Math.log(base);
    }

    public Reciprocal derivative() {
        return new Reciprocal(1, (amplitude * growthRate * 1 / Math.log(base)));
    }

    public double getBase() {
        return base;
    }

    public double getAmplitude() {
        return amplitude;
    }

    public double getGrowthRate() {
        return growthRate;
    }

    @Override
    public String toString() {
        return String.format("%.2f·log_%.2f(%.2f·x)", amplitude, base, growthRate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Logarithmic l)) return false;
        return Double.compare(base, l.base) == 0 &&
                Double.compare(amplitude, l.amplitude) == 0 &&
                Double.compare(growthRate, l.growthRate) == 0;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(base, amplitude, growthRate);
    }




    private static void validateBase(double value) {
        if (value == 1.0 || value < 0.0)
            throw new IllegalArgumentException("Base must be positive and different than 1");
    }
}
