package br.com.math.function;

public class Reciprocal implements Differentiable {

    private final int degree;
    private final double amplitude;

    public Reciprocal() {
        degree = -1;
        amplitude = 1.0;
    }

    public Reciprocal(int degree, double amplitude) {
        this.degree = validateDegree(degree);
        this.amplitude = amplitude;
    }

    public boolean isZeroFunction() {
        return amplitude == 0.0;
    }

    @Override
    public Double apply(Double x) {
        validateValue(x);
        return amplitude * Math.pow(x, degree);
    }

    public Reciprocal derivative() {
        return new Reciprocal(degree - 1, amplitude * degree);
    }

    public Differentiable integral() {
        if (degree == -1) {
            return new Logarithmic(Math.E, amplitude, 1.0);
        }
        return new Reciprocal(degree + 1, amplitude / (degree + 1));
    }


    @Override
    public String toString() {
        return String.format("%.2f·x^%d", amplitude, degree);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reciprocal r)) return false;
        return degree == r.degree && Double.compare(amplitude, r.amplitude) == 0;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(degree, amplitude);
    }




    private static int validateDegree(int value) {
        if (value == 0) throw new IllegalArgumentException("Degree cannot be zero.");
        if (value > 0) return -value;
        return value;
    }
    private void validateValue(double value) {
        if (value == 0) throw new IllegalArgumentException("Function is not defined at x = 0.");
    }

}
