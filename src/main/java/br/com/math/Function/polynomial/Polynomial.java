package br.com.math.function.polynomial;

import br.com.math.function.Integrable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Polynomial implements Integrable {

    private final int degree;

    private final List<Double> coefficients;

    private final double shift;

    public Polynomial(int degree, double coefficient) {
        this(0.0, degree, coefficient);
    }

    public Polynomial(double shift, int degree, double coefficient) {
        this.shift = shift;
        this.degree = validateDegree(degree);
        List<Double> coefficients = new ArrayList<>(
                Collections.nCopies(this.degree + 1, 0.0)
        );
        coefficients.set(this.degree, coefficient);
        this.coefficients = coefficients;
    }

    public Polynomial(double shift, double ... coefficients) {
        this(shift, Arrays.stream(coefficients)
                .boxed()
                .toList());
    }

    public Polynomial(List<Double> coefficients) {
        this(0.0, coefficients);
    }

    public Polynomial(double shift, List<Double> coefficients) {
        this.coefficients = setCoefficients(coefficients);
        this.shift = shift;
        degree = this.coefficients.size() - 1;
    }


    public static Polynomial identity() {
        return new Polynomial(1, 1.0);
    }

    @Override
    public Double apply(Double x) {
        double result = 0.0;
        for (int i = degree; i >= 0; i--) {
            result = (result * (x - shift)) + coefficients.get(i);
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
        List<Double> integralCoefficients = new ArrayList<>(List.of(0.0));
        List<Double> others = IntStream
                .range(0, coefficients.size())
                .mapToObj(i -> coefficients.get(i) / (i + 1))
                .toList();

        integralCoefficients.addAll(others);
        return new Polynomial(integralCoefficients);
    }

    @Override
    public Polynomial multiply(double value) {
        List<Double> result = coefficients.stream()
                .map(c -> value * c)
                .toList();

        return new Polynomial(result);
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
        StringBuilder arg = new StringBuilder();
        if (shift == 0.0) arg.append("x ");
        else if (shift > 0.0) arg.append("(x - ").append((String.format("%.2f", Math.abs(shift)))).append(") ");
        else arg.append("(x + ").append((String.format("%.2f", Math.abs(shift)))).append(") ");
        double coef = 0;
        for (int i = 0; i <= degree; i++) {
            if (Math.abs(get(i)) < 1e-9) {
                continue;
            }
            index = i;
            coef = get(index);
            break;
        }
        if (index == 0) {
            sb.append(String.format("%.2f ", get(0)));
        }
        else if (index == 1) {
            if (coef == 1.0) sb.append(arg);
            else if (coef == -1.0) sb.append("-").append(arg);
            else sb.append(String.format("%.6f·", coef)).append(arg);
        }
        else {
            if (coef == 1.0) sb.append("x").append(superscript(index)).append(" ");
            else if (coef == -1.0) sb.append("-x").append(superscript(index)).append(" ");
            else sb.append(String.format("%.6f·", get(index)))
                        .append("x").append(superscript(index));
        }
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


    /** -------------------------------------------------------------------------------------------------------
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
        StringBuilder arg = new StringBuilder();
        if (shift == 0.0) arg.append("x");
        else if (shift > 0.0) arg.append("(x - ").append((String.format("%.2f", Math.abs(shift)))).append(")");
        else arg.append("(x + ").append(String.format("%.2f", Math.abs(shift))).append(")");
        if (coef == 1.0) {
            part.append("+ ");
        } else if (coef == -1.0) {
            part.append("- ");
        } else if (coef > 0) {
            part.append("+ ").append(String.format("%.6f·", coef));
        } else {
            part.append("- ").append(String.format("%.6f·", Math.abs(coef)));
        }
        if (power == 1) {
            part.append(arg).append(" ");
        } else {
            part.append(arg).append(superscript(power)).append(" ");
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