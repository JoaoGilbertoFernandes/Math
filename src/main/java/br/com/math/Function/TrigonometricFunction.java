package br.com.math.function;

import static br.com.math.function.TrigonometricType.*;

public class TrigonometricFunction implements DifferentiableFunction {

    private final TrigonometricType type;

    public TrigonometricFunction(TrigonometricType type) {
        this.type = type;
    }

    public boolean isZeroFunction() {
        return false;
    }

    public TrigonometricFunction derivative() {
        return switch (type) {
            case SINE -> new TrigonometricFunction(COSINE);
            case COSINE -> new TrigonometricFunction(NEGATIVE_SINE);
            case NEGATIVE_SINE -> new TrigonometricFunction(NEGATIVE_COSINE);
            case NEGATIVE_COSINE -> new TrigonometricFunction(SINE);
        };
    }

    @Override
    public Double apply(Double x) {
        return switch (type) {
            case SINE -> Math.sin(x);
            case COSINE -> Math.cos(x);
            case NEGATIVE_SINE -> -Math.sin(x);
            case NEGATIVE_COSINE -> -Math.cos(x);
        };
    }

    @Override
    public boolean equals(Object o) {
        if (o.equals(this)) return true;
        if (!(o instanceof TrigonometricFunction tf)) return false;
        return type == tf.type;
    }
}
