package br.com.physics;

import br.com.math.Function.DifferentiableFunction;
import br.com.math.Vector.Vector;

import java.util.List;
import java.util.Optional;

public class Motion {

    private final List<Motion1D> motions;

    public Motion(List<Motion1D> motions) {
        this.motions = List.copyOf(motions);
    }

    public List<DifferentiableFunction> position() {
        return motions.stream()
                .map(Motion1D::position)
                .toList();
    }

    public List<DifferentiableFunction> velocity() {
        return motions.stream()
                .map(Motion1D::speed)
                .toList();
    }

    public List<DifferentiableFunction> acceleration() {
        return motions.stream()
                .map(Motion1D::acceleration)
                .toList();
    }

    public Vector positionAt(double time) {
        validateTime(time);
        double[] coordinates = motions.stream()
                .mapToDouble(m -> m.positionAt(time))
                .toArray();

        return new Vector(coordinates);
    }

    public Vector velocityAt(double time) {
        validateTime(time);
        double[] coordinates = motions.stream()
                .mapToDouble(m -> m.speedAt(time))
                .toArray();

        return new Vector(coordinates);
    }

    public Vector accelerationAt(double time) {
        validateTime(time);
        double[] coordinates = motions.stream()
                .mapToDouble(m -> m.accelerationAt(time))
                .toArray();

        return new Vector(coordinates);
    }

    public Vector displacement(double initialTime, double finalTime) {
        validateTimeInterval(initialTime, finalTime);
        return positionAt(finalTime).subtract(positionAt(initialTime));
    }

    public Vector avgVelocity(double initialTime, double finalTime) {
        validateTimeInterval(initialTime, finalTime);
        return displacement(initialTime, finalTime).multiplyByScalar(1 / (finalTime - initialTime));
    }

    public Vector avgAcceleration(double initialTime, double finalTime) {
        validateTimeInterval(initialTime, finalTime);
        return velocityAt(finalTime).subtract(velocityAt(initialTime))
                .multiplyByScalar(1 / (finalTime - initialTime));
    }

    public double speed(double time) {
        return velocityAt(time).norm();
    }

    public double avgSpeed(double initialTime, double finalTime) {
        return avgVelocity(initialTime, finalTime).norm();
    }

    public double accelerationMagnitude(double time) {
        return accelerationAt(time).norm();
    }

    public double avgAccelerationMagnitude(double initialTime, double finalTime) {
        return avgAcceleration(initialTime, finalTime).norm();
    }

    public Optional<Vector> tangentialAcceleration(double time) {
        if (velocityAt(time).isZero()) return Optional.empty();
        return Optional.of(accelerationAt(time).projection(velocityAt(time)));
    }

    public Optional<Vector> normalAcceleration(double time) {
        if (velocityAt(time).isZero()) return Optional.empty();
        return Optional.of(accelerationAt(time).rejection(velocityAt(time)));
    }

    public Optional<Double> tangentialAccelerationMagnitude(double time) {
        if (velocityAt(time).isZero()) return Optional.empty();
        return tangentialAcceleration(time).map(Vector::norm);
    }

    public Optional<Double> normalAccelerationMagnitude(double time) {
        if (velocityAt(time).isZero()) return Optional.empty();
        return normalAcceleration(time).map(Vector::norm);
    }

    public boolean isUniform() {
        return acceleration().stream().allMatch(DifferentiableFunction::isZeroFunction);
    }

    public boolean isUniformlyAccelerated() {
        return acceleration().stream().allMatch(c -> c.derivative().isZeroFunction());
    }

    public boolean isAccelerated(double time) {
        return accelerationAt(time).dotProduct(velocityAt(time)) > 0.0;
    }

    public boolean isDecelerated(double time) {
        return accelerationAt(time).dotProduct(velocityAt(time)) < 0.0;
    }


    private void validateTime(double time) {
        if (time < 0) throw new IllegalArgumentException("Time cannot be negative");
    }

    private void validateTimeInterval(double initialTime, double finalTime) {
        if (finalTime <= initialTime) throw new IllegalArgumentException("Time interval must be positive");
    }
}
