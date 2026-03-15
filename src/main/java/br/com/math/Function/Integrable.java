package br.com.math.function;

public interface Integrable extends Differentiable {

    @Override
    Integrable derivative();

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
        Integrable integral = this;
        for (int i = 0; i < order; i++) {
            integral = integral.integral();
        }
        return integral;
    }

    default Integrable add(Integrable other) {
        return new Integrable() {
            final Integrable self = this;
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
        };
    }

    default Integrable subtract(Integrable other) {
        return add(other.multiply(-1));
    }

    @Override
    default Integrable multiply(double value) {
        return new Integrable() {
            final Integrable self = this;
            @Override
            public Double apply(Double x) {
                return self.apply(x) * value;
            }
            @Override
            public Integrable integral() {
                return self.integral().multiply(value);
            }
            @Override
            public Integrable derivative() {
                return self.derivative().multiply(value);
            }
            @Override
            public boolean isZeroFunction() {
                return self.isZeroFunction() || value == 0;
            }
        };
    }
}
