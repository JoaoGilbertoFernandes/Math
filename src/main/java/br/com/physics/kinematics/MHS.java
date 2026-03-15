package br.com.physics.kinematics;

import br.com.math.function.power.Power;
import br.com.math.function.trigonometric.*;
import br.com.physics.dynamics.force.Force1D;

public class MHS extends Motion1D {

    private final double amplitude;
    private final double frequency;
    private final double phase;

    public MHS(double amplitude, double frequency) {
        this(amplitude, frequency, 0.0);
    }

    public MHS(double amplitude, double frequency, double phase) {
        super(new Cosine(amplitude, frequency, phase));
        this.amplitude = amplitude;
        this.frequency = frequency;
        this.phase = phase;
    }

    public double kinectEnergy(double mass, double x) {
        Power power = new Power(2, mass / 2);
        return power.compose(speed()).apply(x);
    }

    public double potentialEnergy(double mass, double x) {
        double k = mass * frequency * frequency;
        Power power = new Power(2, k / 2);
        return power.compose(position()).apply(x);
    }

    public double mechanicEnergy(double mass) {
        return potentialEnergy(mass, amplitude);
    }

    public Force1D force(double mass) {
        double k = mass * frequency * frequency;
        Cosine f = new Cosine(k * amplitude, frequency, phase);
        return new Force1D(f);
    }
}
