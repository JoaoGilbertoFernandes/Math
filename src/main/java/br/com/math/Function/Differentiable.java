package br.com.math.function;

import br.com.math.function.polynomial.Polynomial;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import static br.com.math.MathUtils.factorial;

public interface Differentiable extends Function<Double, Double> {

    boolean isZeroFunction();

    Differentiable derivative();

    default Differentiable derivative(int order) {
        if (order == 0) return this;
        if (getClass() == Polynomial.class) {
            Polynomial p = (Polynomial) this;
            if (order > p.getDegree()) {
                return Polynomial.zero(0);
            }
        }
        Differentiable result = this;
        for (int i = 0; i < order; i++) {
            result = result.derivative();
        }
        return result;
    }

    default Polynomial taylorSerie(int order, double point) {
        if (this instanceof Polynomial) return (Polynomial) this;
        List<Double> terms = IntStream.range(0, order)
                .mapToObj(i -> derivative(i).apply(point) / factorial(i))
                .toList();

        return new Polynomial(terms);
    }
}
