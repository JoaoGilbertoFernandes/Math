package br.com.math.function;

public abstract class Trigonometric implements Differentiable {

    private final TrigonometricType type;
    private final double coefficient;

    public Trigonometric(TrigonometricType type, double coefficient) {
        this.type = type;
        this.coefficient = coefficient;
    }

    public boolean isZeroFunction() {
        return false;
    }

    public Trigonometric derivative() {
        return switch (type) {
            case SINE -> new Cosine(coefficient);
            case COSINE -> new Sine(-coefficient);
        };
    }

    @Override
    public Double apply(Double x) {
        return switch (type) {
            case SINE -> coefficient * Math.sin(x);
            case COSINE -> coefficient * Math.cos(x);
        };
    }

    @Override
    public boolean equals(Object o) {
        if (o.equals(this)) return true;
        if (!(o instanceof Trigonometric tf)) return false;
        return type == tf.type && Double.compare(coefficient, tf.coefficient) == 0;
    }
}