package br.com.math.function;

import static br.com.math.function.TrigonometricType.COSINE;

public class Cosine extends Trigonometric {

    public Cosine() {
        super(COSINE, 1.0);
    }

    public Cosine(double coefficient) {
        super(COSINE, coefficient);
    }
}
