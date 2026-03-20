package br.com.math.function;

public interface Integrable extends Differentiable {

    @Override
    Integrable derivative();

    @Override
    Integrable multiply(double value);

    Integrable integral();

    @Override
    default Integrable derivative(int order) {
        Integrable result = this;
        for (int i = 0; i < order; i++) {
            result = result.derivative();
        }
        return result;
    }

    default Integrable integral(int order) {
        return integral(order, 0.0);
    }

    default Integrable integral(double constant) {
        return Differentiable.constantFunction(constant).add(integral());
    }

    default Integrable integral(int order, double constant) {
        Integrable integral = this;
        for (int i = 0; i < order; i++) {
            integral = integral.integral(constant);
        }
        return integral;
    }

    default Integrable add(Integrable other) {
        final Integrable self = this;
        return new Integrable() {
            @Override
            public Double apply(Double x) {
                return self.apply(x) + other.apply(x);
            }
            @Override
            public Integrable integral() {
                return self.integral().add(other.integral());
            }
            @Override
            public Integrable derivative() {
                return self.derivative().add(other.derivative());
            }
            @Override
            public boolean isZeroFunction() {
                return self.isZeroFunction() && other.isZeroFunction();
            }
            @Override
            public Integrable multiply(double value) {
                return self.multiply(value).add(other.multiply(value));
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

    default Integrable subtract(Integrable other) {
        return add(other.multiply(-1));
    }
}
