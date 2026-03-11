package br.com.math.function;

import static br.com.math.function.TrigonometricType.SINE;

public class Sine extends Trigonometric {

    public Sine() {
        super(SINE, 1.0, 1.0, 0.0);
    }

    public Sine(double amplitude, double frequency, double phase) {
        super(SINE, amplitude, frequency, phase);
    }
}
