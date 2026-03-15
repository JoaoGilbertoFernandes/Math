package br.com.physics.dynamics.force;

import br.com.math.vector.Vector;

import java.util.List;

public class Force {

    private final List<Force1D> coordinates;
    private Vector vectorForce;

    public Force(List<Force1D> coordinates) {
        this.coordinates = coordinates;
    }

    public double getValue() {
        return vectorForce.norm();
    }

    public double getValueAt(double param) {
        List<Double> list = coordinates.stream()
                .map(force -> getValueAt(param))
                .toList();

        vectorForce = new Vector(list.get(0), list.get(1), list.get(2));
        return vectorForce.norm();
    }

    public List<Force1D> getCoordinates() {
        return coordinates;
    }

    public Vector getVectorForce() {
        return vectorForce;
    }
}
