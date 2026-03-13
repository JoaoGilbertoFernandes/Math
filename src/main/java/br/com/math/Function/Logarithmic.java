package br.com.math.function;

import br.com.math.function.polynomial.Polynomial;
import org.jetbrains.annotations.NotNull;

public record Logarithmic(double base, double amplitude, double rate) implements Differentiable {

    public Logarithmic() {
        this(Math.E, 1.0, 1.0);
    }

    public Logarithmic(double amplitude, double rate) {
        this(Math.E, amplitude, rate);
    }

    public Logarithmic {
        validateBase(base);
    }

    @Override
    public boolean isZeroFunction() {
        return amplitude == 0.0;
    }

    @Override
    public Double apply(Double x) {
        return (amplitude * Math.log(rate * x)) / Math.log(base);
    }

    @Override
    public Reciprocal derivative() {
        return new Reciprocal(-1, (amplitude * rate * 1 / Math.log(base)));
    }

    public Differentiable integral() {
        return multiply(Polynomial.identity())
                .subtract(new Polynomial(1, amplitude / Math.log(base)));
    }

    public Logarithmic changeBase() {
        return changeBase(Math.E);
    }

    public Logarithmic changeBase(double newBase) {
        if (newBase <= 0 || newBase == 1.0) {
            throw new IllegalArgumentException("Base must be positive and different than 1");
        }
        return new Logarithmic(newBase, (amplitude * (Math.log(base) / Math.log(newBase))), rate );
    }

    @Override
    public @NotNull String toString() {
        StringBuilder sb = new StringBuilder("f(x) = ");

        String logPrefix;
        if (base == Math.E) logPrefix = "ln(";
        else if (base == 10.0) logPrefix = "log(";
        else logPrefix = String.format("log<%.2f>(", base);

        if (amplitude != 1.0) sb.append(String.format("%.2f·", amplitude));

        sb.append(logPrefix);
        if (rate == 1.0) sb.append("x)");
        else sb.append(String.format("%.2f·x)", rate));
        return sb.toString().trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Logarithmic other)) return false;
        return Double.compare(changeBase().amplitude(), other.changeBase().amplitude()) == 0 &&
                Double.compare(changeBase().rate(), other.changeBase().rate()) == 0;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(changeBase().amplitude, changeBase().rate());
    }



    private static void validateBase(double value) {
        if (value == 1.0 || value < 0.0)
            throw new IllegalArgumentException("Base must be positive and different than 1");
    }
}
