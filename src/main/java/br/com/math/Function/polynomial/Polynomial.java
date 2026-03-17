package br.com.math.function.polynomial;

import br.com.math.function.Integrable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Polynomial implements Integrable {

    private final int degree;

    private final List<Double> coefficients;

    public Polynomial(int degree, double coefficient) {
        this.degree = validateDegree(degree);
        List<Double> coefficients = new ArrayList<>(
                Collections.nCopies(this.degree + 1, 0.0)
        );
        coefficients.set(this.degree, coefficient);
        this.coefficients = coefficients;
    }

    public Polynomial(List<Double> coefficients) {
        this.coefficients = setCoefficients(coefficients);
        degree = this.coefficients.size() - 1;
    }


    public static Polynomial identity() {
        return new Polynomial(1, 1.0);
    }

    @Override
    public Double apply(Double x) {
        if (x == 0) return coefficients.getFirst();
        double result = 0.0;
        for (int i = degree; i >= 0; i--) {
            result = (result * x) + coefficients.get(i);
        }
        return result;
    }

    @Override
    public Polynomial derivative() {
        if (degree == 0) return new Polynomial(List.of(0.0));
        List<Double> derivativeCoefficients = IntStream
                .range(1, coefficients.size())
                .mapToObj(i -> coefficients.get(i) * i)
                .toList();

        return new Polynomial(derivativeCoefficients);
    }

    @Override
    public Polynomial integral() {
        double c = ThreadLocalRandom.current().nextDouble(0.0, 10.0);
        List<Double> integralCoefficients = new ArrayList<>(List.of(c));
        List<Double> others = IntStream
                .range(0, coefficients.size())
                .mapToObj(i -> coefficients.get(i) / (i + 1))
                .toList();

        integralCoefficients.addAll(others);
        return new Polynomial(integralCoefficients);
    }

    public double get(int index) {
        return coefficients.get(index);
    }

    public int degree() {
        return degree;
    }

    public List<Double> coefficients() {
        return coefficients;
    }

    public boolean isZeroFunction() {
        return coefficients.stream().allMatch(c -> c == 0.0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (int i = 0; i <= degree; i++) {
            if (get(i) == 0.0) {
                continue;
            }
            index = i;
            break;
        }
        if (index == 0) sb.append(String.format("%.2f ", get(0)));
        else if (index == 1) sb.append("x ");
        else sb.append(String.format("%.2f·", get(index))).append("x").append(superscript(index));
        for (int i = index + 1; i <= degree; i++) {
            sb.append(formatCoefficients(get(i), i));
        }
        return sb.toString().trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Polynomial pf)) return false;
        for (int i = 0; i <= degree; i++) {
            if (Math.abs(get(i) - pf.get(i)) > 1e-12) {
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
            if (Math.abs(coef) > 1e-12) {
                double normalized = Math.round(coef * 1e12) / 1e12;
                result = 31 * result + Double.hashCode(normalized);
                result = 31 * result + i;
            }
        }
        return result;
    }


    /** --------------------------------------------------------------------------------------------------------------
     * PRIVATE METHODS
     */

    private static int validateDegree(int degree) {
        return Math.abs(degree);
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
        if (Math.abs(coef) < 1e-12) return "";
        StringBuilder part = new StringBuilder();
        if (coef == 1.0) {
            part.append("+ ");
        } else if (coef == -1.0) {
            part.append("- ");
        } else if (coef > 0) {
            part.append("+ ").append(String.format("%.2f·", coef));
        } else {
            part.append("- ").append(String.format("%.2f·", Math.abs(coef)));
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
                case '0': sb.append("⁰"); break;
                case '1': sb.append("¹"); break;
                case '2': sb.append("²"); break;
                case '3': sb.append("³"); break;
                case '4': sb.append("⁴"); break;
                case '5': sb.append("⁵"); break;
                case '6': sb.append("⁶"); break;
                case '7': sb.append("⁷"); break;
                case '8': sb.append("⁸"); break;
                case '9': sb.append("⁹"); break;
            }
        }
        return sb.toString();
    }
}