package br.com.math.function.trigonometric;

import br.com.math.function.Integrable;

public abstract class Trigonometric implements Integrable {

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

    @Override
    public boolean isZeroFunction() {
        return amplitude == 0.0;
    }

    @Override
    public Trigonometric derivative() {
        return switch (type) {
            case SINE -> new Cosine((amplitude * frequency), frequency, phase);
            case COSINE -> new Sine((-amplitude * frequency), frequency, phase);
        };
    }

    @Override
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

    public TrigonometricType type() {
        return type;
    }

    public double amplitude() {
        return amplitude;
    }

    public double frequency() {
        return frequency;
    }

    public double phase() {
        return phase;
    }

    public double period() {
        return (2 * Math.PI) / frequency;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (Math.abs(amplitude) != 1.0) {
            sb.append(String.format("%.2f·", amplitude));
        }
        else if (amplitude < 0.0) {
            sb.append("-");
        }
        String func = switch (type) {
            case SINE -> "sin(";
            case COSINE -> "cos(";
        };
        sb.append(func);
        if (frequency != 1.0) {
            sb.append(String.format("%.2f·x", frequency));
        } else {
            sb.append("x");
        }
        if (phase != 0.0) {
            sb.append(String.format(" + %.2f", phase));
        }
        sb.append(")");
        return sb.toString().trim();
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