package br.com.math.function;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import static br.com.math.MathUtils.factorial;

public interface DifferentiableFunction extends Function<Double, Double> {

    boolean isZeroFunction();

    DifferentiableFunction derivative();

    default DifferentiableFunction derivative(int order) {
        if (order == 0) return this;
        DifferentiableFunction result = this;
        for (int i = 0; i <= order; i++) {
            result = result.derivative();
        }
        return result;
    }

    default PolynomialFunction taylorSerie(int order, double point) {
        if (this instanceof PolynomialFunction) return (PolynomialFunction) this;
        List<Double> terms = IntStream.range(0, order)
                .mapToObj(i -> derivative(i).apply(point) / factorial(i))
                .toList();

        return new PolynomialFunction(terms);
    }
}
