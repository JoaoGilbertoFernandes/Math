package br.com.math.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class PolynomialFunction implements DifferentiableFunction {

    private final int degree;

    private final List<Double> coefficients;

    public PolynomialFunction(List<Double> coefficients) {
        this.coefficients = setCoefficients(coefficients);
        this.degree = this.coefficients.size() - 1;
    }

    public static PolynomialFunction zero(int degree) {
        return computeZero(degree);
    }

    @Override
    public Double apply(Double x) {
        return computeApply(x);
    }

    public PolynomialFunction derivative() {
        return computeDerivative();
    }

    @Override
    public PolynomialFunction derivative(int order) {
        if (order == 0) return this;
        PolynomialFunction result = this;
        for (int i = 0; i < order; i++) {
            result = result.derivative();
        }
        return result;
    }

    public PolynomialFunction integral() {
        return computeIntegral();
    }

    public PolynomialFunction integral(int order) {
        return computeIntegral(order);
    }

    public double get(int index) {
        return coefficients.get(index);
    }

    public int getDegree() {
        return degree;
    }

    public List<Double> getCoefficients() {
        return coefficients;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("f(x) = ").append(String.format("%.2f ", get(0)));
        for (int i = 1; i <= degree; i++) {
            sb.append(formatCoefficients(get(i), i));
        }
        return sb.toString().trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PolynomialFunction pf)) return false;
        for (int i = 0; i <= degree; i++) {
            if (Math.abs(get(i) - pf.get(i)) > 1e-9) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 17;
        for (int i = 0; i < coefficients.size(); i++) {
            double coef = coefficients.get(i);
            if (Math.abs(coef) > 1e-9) {
                double normalized = Math.round(coef * 1e9) / 1e9;
                result = 31 * result + Double.hashCode(normalized);
                result = 31 * result + i;
            }
        }
        return result;
    }




    /** PRIVATE METHODS */

    public static PolynomialFunction computeZero(int degree) {
        List<Double> coefficients = IntStream
                .range(0, degree)
                .mapToObj(i -> 0.0)
                .toList();

        return new PolynomialFunction(coefficients);
    }

    private double computeApply(Double value) {
        if (value == 0) return coefficients.getFirst();
        double result = 0.0;
        for (int i = degree; i >= 0; i--) {
            result = (result * value) + coefficients.get(i);
        }
        return result;
    }

    private PolynomialFunction computeDerivative() {;
        List<Double> derivativeCoefficients = IntStream
                .range(1, coefficients.size())
                .mapToObj(i -> coefficients.get(i) * i)
                .toList();

        return new PolynomialFunction(derivativeCoefficients);
    }

    private PolynomialFunction computeIntegral() {
        double c = ThreadLocalRandom.current().nextDouble(0.0, 10.0);
        List<Double> integralCoefficients = new ArrayList<>(List.of(c));
        List<Double> others = IntStream
                .range(0, coefficients.size())
                .mapToObj(i -> coefficients.get(i) / (i + 1))
                .toList();

        integralCoefficients.addAll(others);
        return new PolynomialFunction(integralCoefficients);
    }

    private PolynomialFunction computeIntegral(int order) {
        PolynomialFunction integral = this;
        for (int i = 0; i < order; i++) {
            integral = integral.computeIntegral();
        }
        return integral;
    }

    private static int setDegree(List<Double> coefficients) {
        for (int i = coefficients.size() - 1 ; i > 0; i--) {
            if (coefficients.get(i) != 0.0) return i;
        }
        return 0;
    }

    private static List<Double> setCoefficients(List<Double> coefficients) {
        int degree = setDegree(coefficients);
        return coefficients.subList(0, degree + 1);
    }

    private String formatCoefficients(double coef, int power)  {
        if (Math.abs(coef) < 1e-9) return "";
        StringBuilder part = new StringBuilder();
        if (coef == 1.0) {
            part.append("+ ");
        } else if (coef == -1.0) {
            part.append("- ");
        } else if (coef > 0) {
            part.append("+ ").append(String.format("%.2f", coef));
        } else {
            part.append("- ").append(String.format("%.2f", Math.abs(coef)));
        }
        if (power == 1) {
            part.append("x ");
        } else {
            part.append("x").append(superscript(power)).append(" ");
        }
        return part.toString();
    }

    protected String superscript(int power) {
        String digits = String.valueOf(power);
        StringBuilder sb = new StringBuilder();
        for (char c : digits.toCharArray()) {
            switch (c) {
                case '0': sb.append("\u2070"); break;
                case '1': sb.append("\u00B9"); break;
                case '2': sb.append("\u00B2"); break;
                case '3': sb.append("\u00B3"); break;
                case '4': sb.append("\u2074"); break;
                case '5': sb.append("\u2075"); break;
                case '6': sb.append("\u2076"); break;
                case '7': sb.append("\u2077"); break;
                case '8': sb.append("\u2078"); break;
                case '9': sb.append("\u2079"); break;
            }
        }
        return sb.toString();
    }
}
