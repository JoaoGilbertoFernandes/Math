package br.com.math.function;

public class ReciprocalFunction implements DifferentiableFunction {

    private final int degree;
    private final double coefficient;

    public ReciprocalFunction() {
        degree = -1;
        coefficient = 1.0;
    }

    public ReciprocalFunction(int degree, double coefficients) {
        this.degree = validateDegree(degree);
        this.coefficient = coefficients;
    }

    public boolean isZeroFunction() {
        return coefficient == 0.0;
    }

    @Override
    public Double apply(Double x) {
        validateValue(x);
        return coefficient * Math.pow(x, degree);
    }

    public ReciprocalFunction derivative() {
        return new ReciprocalFunction(degree - 1, coefficient * degree);
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
