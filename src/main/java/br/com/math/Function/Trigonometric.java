package br.com.math.function;

public abstract class Trigonometric implements Differentiable {

    private final TrigonometricType type;

    private final double amplitude;

    private final double frequency;

    private final double phase;

    public Trigonometric(TrigonometricType type, double amplitude, double frequency, double phase) {
        this.type = type;
        this.amplitude = amplitude;
        this.frequency = frequency;
        this.phase = phase;
    }

    public boolean isZeroFunction() {
        return amplitude == 0.0;
    }

    public Trigonometric derivative() {
        return switch (type) {
            case SINE -> new Cosine((amplitude * frequency), frequency, phase);
            case COSINE -> new Sine((-amplitude * frequency), frequency, phase);
        };
    }

    public Trigonometric integral() {
        return switch (type) {
            case SINE -> new Cosine((-amplitude / frequency), frequency, phase);
            case COSINE -> new Sine((amplitude / frequency), frequency, phase);
        };
    }

    @Override
    public Double apply(Double x) {
        return switch (type) {
            case SINE -> amplitude * Math.sin(frequency * x + phase);
            case COSINE -> amplitude * Math.cos(frequency * x + phase);
        };
    }

    public double getAmplitude() {
        return amplitude;
    }

    public double getFrequency() {
        return frequency;
    }

    public double getPhase() {
        return phase;
    }

    public double getPeriod() {
        return (2 * Math.PI) / frequency;
    }

    @Override
    public String toString() {
        return switch (type) {
            case SINE -> String.format("%.2f·sin(%.2f·x + %.2f)", amplitude, frequency, phase);
            case COSINE -> String.format("%.2f·cos(%.2f·x + %.2f)", amplitude, frequency, phase);
        };
    }

    @Override
    public boolean equals(Object o) {
        if (o.equals(this)) return true;
        if (!(o instanceof Trigonometric tf)) return false;
        return type == tf.type && Double.compare(amplitude, tf.amplitude) == 0;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(type, amplitude, frequency, phase);
    }

}