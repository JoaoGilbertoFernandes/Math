package br.com.physics;

import br.com.math.Function.DifferentiableFunction;


public class Motion1D {

    private final DifferentiableFunction positionFunction;
    private final DifferentiableFunction speedFunction;
    private final DifferentiableFunction accelerationFunction;
    private static final double EPSILON = 1e-9;

    public Motion1D(DifferentiableFunction equationOfMotion) {
        positionFunction = equationOfMotion;
        speedFunction = equationOfMotion.derivative();
        accelerationFunction = equationOfMotion.derivative(2);
    }

    public DifferentiableFunction position() {
        return positionFunction;
    }

    public DifferentiableFunction speed() {
        return speedFunction;
    }

    public DifferentiableFunction acceleration() {
        return accelerationFunction;
    }

    public double positionAt(double time) {
        validateTime(time);
        return position().apply(time);
    }

    public double speedAt(double time) {
        validateTime(time);
        return speed().apply(time);
    }

    public double accelerationAt(double time) {
        validateTime(time);
        return acceleration().apply(time);
    }

    public double avgSpeed(double initialTime, double finalTime) {
        validateTimeInterval(initialTime, finalTime);
        return (positionAt(finalTime) - positionAt(initialTime)) / (finalTime - initialTime);
    }

    public double avgAcceleration(double initialTime, double finalTime) {
        validateTimeInterval(initialTime, finalTime);
        return (speedAt(finalTime) - speedAt(initialTime)) / (finalTime - initialTime);
    }

    public boolean isForward(double time) {
        validateTime(time);
        return speedAt(time) > 0.0;
    }

    public boolean isBackward(double time) {
        validateTime(time);
        return speedAt(time) < 0.0;
    }

    public boolean isInRest(double time) {
        return Math.abs(speedAt(time)) < EPSILON;
    }


    private void validateTime(double time) {
        if (time < 0) throw new IllegalArgumentException("Time cannot be negative");
    }

    private void validateTimeInterval(double initialTime, double finalTime) {
        if (finalTime <= initialTime) throw new IllegalArgumentException("Time interval must be positive");
    }
}
