package br.com.physics.kinematics;

import br.com.math.function.Differentiable;


public class Motion1D {

    private final Differentiable positionFunction;
    private final Differentiable speedFunction;
    private final Differentiable accelerationFunction;

    public Motion1D(Differentiable equationOfMotion) {
        positionFunction = equationOfMotion;
        speedFunction = equationOfMotion.derivative();
        accelerationFunction = equationOfMotion.derivative(2);
    }

    public Differentiable getPositionFunction() {
        return positionFunction;
    }

    public Differentiable getSpeedFunction() {
        return speedFunction;
    }

    public Differentiable getAccelerationFunction() {
        return accelerationFunction;
    }

    public double getPosition(double time) {
        validateTime(time);
        return getPositionFunction().apply(time);
    }

    public double getSpeed(double time) {
        validateTime(time);
        return getSpeedFunction().apply(time);
    }

    public double getAcceleration(double time) {
        validateTime(time);
        return getAccelerationFunction().apply(time);
    }

    public double avgSpeed(double initialTime, double finalTime) {
        validateTimeInterval(initialTime, finalTime);
        return (getPosition(finalTime) - getPosition(initialTime)) / (finalTime - initialTime);
    }

    public double avgAcceleration(double initialTime, double finalTime) {
        validateTimeInterval(initialTime, finalTime);
        return (getSpeed(finalTime) - getSpeed(initialTime)) / (finalTime - initialTime);
    }

    public boolean isForward(double time) {
        validateTime(time);
        return getSpeed(time) > 0.0;
    }

    public boolean isBackward(double time) {
        validateTime(time);
        return getSpeed(time) < 0.0;
    }

    public boolean isInRest(double time) {
        return Math.abs(getSpeed(time)) < 0.0;
    }

    public boolean isAccelerated(double time) {
        return getAcceleration(time) * getSpeed(time) > 0.0;
    }

    public boolean isDecelerated(double time) {
        return getAcceleration(time) * getSpeed(time) < 0.0;
    }

    public boolean isUniform() {
        return accelerationFunction.isZeroFunction();
    }

    public boolean isUniformlyAccelerated() {
        return accelerationFunction.derivative().isZeroFunction();
    }


    private void validateTime(double time) {
        if (time < 0) throw new IllegalArgumentException("Time cannot be negative");
    }

    private void validateTimeInterval(double initialTime, double finalTime) {
        if (finalTime <= initialTime) throw new IllegalArgumentException("Time interval must be positive");
    }
}
