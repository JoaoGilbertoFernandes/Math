package br.com.math.function.power;

import br.com.math.function.Differentiable;
import br.com.math.function.exponential.Logarithmic;

public class Power implements Differentiable {

    private final double degree;
    private final double coefficient;

    public Power(double coefficient) {
        degree = 0.5;
        this.coefficient = coefficient;
    }

    public Power(double degree, double coefficient) {
        this.degree = degree;
        this.coefficient = coefficient;
    }


    @Override
    public boolean isZeroFunction() {
        return coefficient == 0;
    }

    @Override
    public Double apply(Double x) {
        if (degree < 0) validateValue(x);
        return coefficient * Math.pow(x, degree);
    }

    @Override
    public Differentiable derivative() {
        return new Power(degree - 1, coefficient * degree);
    }

    public Differentiable integral() {
        if (degree == -1.0) {
            return new Logarithmic(Math.E, coefficient, 1.0);
        }
        return new Power(degree + 1, coefficient / (degree + 1));
    }

    @Override
    public Power multiply(double value) {
        return new Power(degree, value * coefficient);
    }

    public double degree() {
        return degree;
    }

    public double coefficient() {
        return coefficient;
    }

    @Override
    public String toString() {
        if (coefficient == 0.0) return Differentiable.zeroFunction().toString();
        StringBuilder sb = new StringBuilder();
        if (Math.abs(coefficient) != 1.0) {
            sb.append(String.format("%.2f·", coefficient));
        } else if (coefficient < 0.0) {
            sb.append("-");
        }
        sb.append("x^");
        if (Math.abs(degree) != 1.0) {
            sb.append(String.format("%.2f", degree));
        }
        else if (degree < 0.0) {
            sb.append("-1");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Power p)) return false;
        return degree == p.degree && Double.compare(coefficient, p.coefficient) == 0;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(degree, coefficient);
    }




    private void validateValue(double value) {
        if (value == 0) throw new IllegalArgumentException("Function is not defined at x = 0.");
    }
}
