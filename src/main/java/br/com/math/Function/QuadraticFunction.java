package br.com.math.Function;

import java.util.List;

public class QuadraticFunction extends PolynomialFunction {

    private final Double constant;
    private final Double linear;
    private final Double quadratic;

    public QuadraticFunction(Double constant, Double linear, Double quadratic) {
        validateCoefficient(quadratic);
        super(List.of(constant, linear, quadratic));
        this.constant = constant;
        this.linear = linear;
        this.quadratic = quadratic;
    }

    @Override
    public Double apply(Double x) {
        return quadratic * x * x + linear * x + constant;
    }

    public double[] zeros() {
        return computeZero();
    }

    public double[] vertex() {
        double[] vertex = new double[2];
        vertex[0] = (-1.0 * linear) / (2.0 * quadratic);
        vertex[1] = apply(vertex[0]);
        return vertex;
    }

    public String factoredForm() {
        return factoredFormatter();
    }

    public String vertexForm() {
        return vertexFormatter();
    }


    private static void validateCoefficient(double value) {
        if (value == 0) throw new IllegalArgumentException("Quadratic coefficient cannot be zero") ;
    }

    private double[] computeZero() {
        double[] result = new double[2];
        double delta = linear * linear - 4.0 * quadratic * constant;
        if (delta < 0.0) {
            throw new ArithmeticException("Function has no real roots");
        }
        if (delta == 0.0) {
            result[0] = -1.0 * linear / 2.0 * quadratic;
            result[1] = result[0];
        }
        if (delta > 0.0) {
            result[0] = (-1.0 * linear + Math.sqrt(delta)) / 2.0 * quadratic;
            result[1] = (-1.0 * linear - Math.sqrt(delta)) / 2.0 * quadratic;
        }
        return result;
    }

    private String factoredFormatter() {

        return "f(x) = " +
                String.format("%.2f(x %s %.2f)", quadratic,
                        zeros()[0] >= 0.0 ? "+" : "-", Math.abs(zeros()[0])) +
                "(x " +
                String.format("%s %.2f)", zeros()[1] >= 0.0 ? "+" : "-", Math.abs(zeros()[1]));
    }

    private String vertexFormatter() {
        String fit = quadratic != 1.00 ? String.format("%.2f(x %s %.2f)", quadratic,
                vertex()[0] >= 0.0 ? "-" : "+", Math.abs(vertex()[0])) :
                String.format("(x %s %.2f)", vertex()[0] >= 0.0 ? "-" : "+", Math.abs(vertex()[0]));
        return "f(x) = " +
                fit +
                superscript(2) +
                String.format(" %s %.2f", vertex()[1] >= 0.0 ? "+" : "-", Math.abs(vertex()[1]));
    }
}
