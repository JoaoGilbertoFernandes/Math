package br.com.math.function.power;

public class Reciprocal extends Power {

    public Reciprocal() {
        super(-1,1.0);
    }

    public Reciprocal(int degree, double amplitude) {
        super(validateDegree(degree), amplitude);
    }

    @Override
    public String toString() {
        return String.format("%.2f·x^%d", getCoefficient(), (int) getDegree());
    }

    private static double validateDegree(double value) {
        if (value == 0) throw new IllegalArgumentException("Degree cannot be zero.");
        return -Math.abs(value);
    }
}
