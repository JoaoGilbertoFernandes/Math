package br.com.math.function;

import br.com.math.function.polynomial.Polynomial;
import br.com.math.function.power.Reciprocal;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import static br.com.math.MathUtils.factorial;

public interface Differentiable extends Function<Double, Double> {

    static Integrable zeroFunction() {
        return new Integrable() {
            @Override
            public Double apply(Double x) {
                return 0.0;
            }
            @Override
            public Integrable derivative() {
                return this;
            }
            @Override
            public Integrable integral() {
                return this;
            }
            @Override
            public boolean isZeroFunction() {
                return true;
            }
        };
    }

    static Integrable constantFunction(double value) {
        return new Integrable() {
            @Override
            public Double apply(Double x) {
                return value;
            }
            @Override
            public Integrable derivative() {
                return zeroFunction();
            }
            @Override
            public Integrable integral() {
                return new Polynomial(1, value);
            }
            @Override
            public boolean isZeroFunction() {
                return false;
            }
        };
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
        };
    }

    default Differentiable subtract(Differentiable other) {
        return add(other.multiply(-1));
    }

    default Differentiable multiply(double value) {
        return multiply(Differentiable.constantFunction(value));
    }

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
        };
    }

    default Differentiable divide(Differentiable other) {
        Differentiable self = this;
        return new Differentiable() {
            @Override
            public Double apply(Double x) {
                if (other.apply(x) == 0.0) {
                    throw new ArithmeticException("Division by zero at x = " + x);
                }
                return self.apply(x) / other.apply(x);
            }
            @Override
            public Differentiable derivative() {
                Differentiable invOther = new Reciprocal().compose(other);
                return multiply(invOther).derivative();
            }
            @Override
            public boolean isZeroFunction() {
                return self.isZeroFunction();
            }
        };
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
