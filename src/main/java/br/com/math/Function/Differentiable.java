package br.com.math.function;

import br.com.math.function.polynomial.Polynomial;
import br.com.math.function.power.Reciprocal;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import static br.com.math.MathUtils.factorial;

public interface Differentiable extends Function<Double, Double> {

    static Polynomial zeroFunction() {
        return new Polynomial(0, 0);
    }

    static Polynomial constantFunction(double value) {
        return new Polynomial(0, value);
    }

    boolean isZeroFunction();

    Differentiable derivative();

    default Differentiable derivative(int order) {
        Differentiable result = this;
        for (int i = 0; i < order; i++) {
            result = result.derivative();
        }
        return result;
    }

    default Differentiable add(Differentiable other) {
        Differentiable self = this;
        return new Differentiable() {
            @Override
            public Double apply(Double x) {
                return self.apply(x) + other.apply(x);
            }
            @Override
            public Differentiable derivative() {
                return self.derivative().add(other.derivative());
            }
            @Override
            public boolean isZeroFunction() {
                return self.isZeroFunction() && other.isZeroFunction();
            }
            @Override
            public Differentiable multiply(double value) {
                return self.add(other).multiply(value);
            }
            @Override
            public String toString() {
                if (other.isZeroFunction()) {
                    return self.toString();
                }
                if (self.isZeroFunction()) {
                    return other.toString();
                }
                return self + " + " + other;
            }
        };
    }

    default Differentiable subtract(Differentiable other) {
        return add(other.multiply(-1));
    }

    Differentiable multiply(double value);

    default Differentiable multiply(Differentiable other) {
        Differentiable self = this;
        return new Differentiable() {
            @Override
            public Double apply(Double x) {
                return self.apply(x) * other.apply(x);
            }
            @Override
            public Differentiable derivative() {
                return self.derivative().multiply(other).add(self.multiply(other.derivative()));
            }
            @Override
            public boolean isZeroFunction() {
                return self.isZeroFunction() || other.isZeroFunction();
            }
            @Override
            public Differentiable multiply(double value) {
                return self.multiply(other).multiply(constantFunction(value));
            }
            @Override
            public String toString() {
                if (isZeroFunction() || other.isZeroFunction()) {
                    return zeroFunction().toString();
                }
                return "(" + self + ")" + "·" + "(" + other.toString() + ")";
            }
        };
    }

    default Differentiable divide(Differentiable other) {
        return multiply(new Reciprocal().compose(other));
    }

    default Differentiable compose(Differentiable inner) {
        Differentiable outer = this;
        return new Differentiable() {
            @Override
            public Double apply(Double x) {
                return outer.apply(inner.apply(x));
            }
            @Override
            public Differentiable derivative() {
                return outer.derivative().compose(inner).multiply(inner.derivative());
            }
            @Override
            public boolean isZeroFunction() {
                return outer.compose(inner).isZeroFunction();
            }
            @Override
            public Differentiable multiply(double value) {
                return compose(inner).multiply(value);
            }
            @Override
            public String toString() {
                return outer.toString().replace("x", "(" + inner.toString() + ")");
            }
        };
    }

    default Polynomial taylorSerie(int order, double point) {
        if (this instanceof Polynomial) return (Polynomial) this;
        List<Double> terms = IntStream.range(0, order)
                .mapToObj(i -> derivative(i).apply(point) / factorial(i))
                .toList();

        return new Polynomial(terms);
    }
}
