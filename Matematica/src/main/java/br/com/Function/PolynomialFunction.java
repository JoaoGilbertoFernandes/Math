package br.com.Function;

import lombok.Getter;
import lombok.NonNull;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class PolynomialFunction implements Function<Double, Double> {

    @Getter
    private final int degree;
    @Getter
    private final List<Double> coefficients;

    public PolynomialFunction(List<Double> coefficients) {
        this.coefficients = setCoefficients(coefficients);
        this.degree = this.coefficients.size() - 1;
    }

    @Override
    public Double apply(Double x) {
        return computeApply(x);
    }

    public PolynomialFunction derivative() {
        return computeDerivative();
    }

    public PolynomialFunction derivative(int order) {
        return computeDerivative(order);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("f(x) = ").append(String.format("%.2f ",coefficients.get(0)));
        if (coefficients.size() > 1) {
            if (coefficients.get(1) != 0.00) sb.append(String.format("%+.2fx ", coefficients.get(1)));
            for (int i = 2; i <= degree; i++) {
                if (coefficients.get(i) != 0.0)
                    sb.append(String.format("%+.2fx^%d ", coefficients.get(i), i));
            }
        }
        return sb.toString();
    }





    private double computeApply(Double value) {
        if (value == 0) return coefficients.getFirst();
        double result = 0.0;
        for (int i = degree; i >= 0; i--) {
            result = (result * value) + coefficients.get(i);
        }
        return result;
    }

    /*
    private @NonNull PolynomialFunction computeDerivative(int order) {;
        PolynomialFunction derivative = this;
        if (order == 0) return this;
        for (int i = 0; i < order; i++) {
            PolynomialFunction nextDerivative = derivative;
            List<Double> derivativeCoefficients = IntStream
                   .range(1, nextDerivative.coefficients.size())
                   .mapToDouble(j -> nextDerivative.coefficients.get(j) * j)
                   .boxed()
                   .toList();
            derivative = new PolynomialFunction(
                    derivativeCoefficients.isEmpty() ? List.of(0.0) : derivativeCoefficients
            );
       }
        return derivative;
    }
    */

    private @NonNull PolynomialFunction computeDerivative() {;
        List<Double> derivativeCoefficients = IntStream
                .range(1, coefficients.size())
                .mapToDouble(j -> coefficients.get(j) * j)
                .boxed()
                .toList();

        return new PolynomialFunction(derivativeCoefficients);
    }

    private @NonNull PolynomialFunction computeDerivative(int order) {
        PolynomialFunction derivative = this;
        for (int i = 1; i < order; i++) {
            derivative = derivative.derivative();
        }
        return derivative;
    }

    private static int setDegree(@NonNull List<Double> coefficients) {
        for (int i = coefficients.size() - 1 ; i > 0; i--) {
            if (coefficients.get(i) != 0.0) return i;
        }
        return 0;
    }

    private static @NonNull List<Double> setCoefficients(@NonNull List<Double> coefficients) {
        int degree = setDegree(coefficients);
        return coefficients.subList(0, degree + 1);
    }
}
