package br.com.math.function;

public interface Integrable extends Differentiable {

    Integrable integral();

    default Differentiable integral(int order) {
        Integrable integral = this;
        for (int i = 0; i < order; i++) {
            integral = integral.integral();
        }
        return integral;
    }
}
