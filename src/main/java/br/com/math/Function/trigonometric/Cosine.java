package br.com.math.function.trigonometric;

import static br.com.math.function.trigonometric.TrigonometricType.COSINE;

public class Cosine extends Trigonometric {

    public Cosine() {
        super(COSINE, 1.0, 1.0, 0.0);
    }

    public Cosine(double amplitude, double frequency, double phase) {
        super(COSINE, amplitude, frequency, phase);
    }
}
