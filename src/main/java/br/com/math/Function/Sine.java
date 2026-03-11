package br.com.math.function;

import static br.com.math.function.TrigonometricType.SINE;

public class Sine extends Trigonometric {

    public Sine() {
        super(SINE, 1.0);
    }

    public Sine(double coefficient) {
        super(SINE, coefficient);
    }
}
