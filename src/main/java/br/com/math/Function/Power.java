package br.com.math.function;

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

    public double getDegree() {
        return degree;
    }

    public double getCoefficient() {
        return coefficient;
    }

    @Override
    public String toString() {
        return String.format("%.2f·x^%.2f", coefficient, degree);
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
