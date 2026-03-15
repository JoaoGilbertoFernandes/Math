package br.com.math.function.exponential;

import br.com.math.function.Integrable;
import org.jetbrains.annotations.NotNull;

public record Exponential(double base, double amplitude, double rate) implements Integrable {

    public Exponential() {
        this(Math.E, 1.0, 1.0);
    }

    public Exponential(double amplitude, double rate) {
        this(Math.E, amplitude, rate);
    }

    @Override
    public boolean isZeroFunction() {
        return amplitude == 0.0;
    }

    @Override
    public Double apply(Double x) {
        return amplitude * Math.pow(base, rate * x);
    }

    @Override
    public Exponential derivative() {
        return new Exponential(base, (amplitude * rate * Math.log(base)), rate);
    }

    @Override
    public Exponential integral() {
        return new Exponential(base, (amplitude / (rate * Math.log(base))), rate);
    }

    public Exponential changeBase() {
        return changeBase(Math.E);
    }

    public Exponential changeBase(double newBase) {
        if (newBase <= 0 || newBase == 1.0) {
            throw new IllegalArgumentException("Base must be positive and different than 1");
        }
        return new Exponential(newBase, amplitude, (rate * (Math.log(base) / Math.log(newBase))));
    }

    @Override
    public @NotNull String toString() {
        if (base == Math.E) return String.format("%.2f·e^(%.2f·x)", amplitude, rate);
        return String.format("%.2f·%s^(%.2f·x)", amplitude, base, rate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exponential other)) return false;
        return Double.compare(changeBase().amplitude(), other.changeBase().amplitude()) == 0 &&
                Double.compare(changeBase().rate(), other.changeBase().rate()) == 0;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(changeBase().amplitude, changeBase().rate());
    }


}
