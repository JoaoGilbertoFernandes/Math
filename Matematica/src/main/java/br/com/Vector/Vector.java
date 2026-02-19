package br.com.Vector;

import br.com.Matrix.*;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Vector {

    @Getter
    protected final int size;
    protected final ColumnMatrix coordinates;

    public Vector(int size) {
        this.size = size;
        coordinates = new ColumnMatrix(size);
    }

    public Vector(double @NonNull[] data) {
        size = data.length;
        coordinates = new ColumnMatrix(data);
    }

    public Vector(@NonNull ColumnMatrix coordinates) {
        size = coordinates.getSize();
        this.coordinates = coordinates;
    }


    public static @NonNull Vector zero(int size) {
        return new Vector(size);
    }

    public static @NonNull Vector unit(int size) {
        double[] data = new double[size];
        Arrays.fill(data, 1.0);
        return new Vector(data);
    }

    public Vector multiplyByScalar(double value) {
        return new Vector(coordinates.multiplyByScalar(value));
    }

    public @NonNull Vector add(@NonNull Vector other) {
        return new Vector(coordinates.add(other.coordinates));
    }

    public @NonNull Vector subtract(@NonNull Vector other) {
        return add(other.multiplyByScalar(-1));
    }

    public double dotProduct(@NonNull Vector other) {
        validateSize(other);
        return coordinates.multiplyBy(other.coordinates.transpose());
    }

    public @NonNull Matrix outerProduct(@NonNull Vector other) {
        return coordinates.multiply(other.coordinates.transpose());
    }

    public @NonNull Vector projection(@NonNull Vector other) {
        return other.normalized().multiplyByScalar(dotProduct(other.normalized()));
    }

    public @NonNull Vector rejection(@NonNull Vector other) {
        return subtract(projection(other));
    }

    public @NonNull Decomposition decompose(@NonNull Vector other) {
        return new Decomposition(projection(other), rejection(other));
    }

    public double norm() {
        return Math.sqrt(dotProduct(this));
    }

    public Vector normalized() {
        validateNormalization();
        return multiplyByScalar(Math.pow(norm(), -1));
    }

    public double cosSimilarity(@NonNull Vector other) {
        validateSize(other);
        if (norm() == 0 || other.norm() == 0) return 0;
        double value = dotProduct(other) * Math.pow(norm() * other.norm(), -1);
        return Math.max(-1.0, Math.min(1.0, value));
    }

    public double angle(@NonNull Vector other) {
        validateSize(other);
        return Math.acos(cosSimilarity(other));
    }

    public double angleDeg(@NonNull Vector other) {
        validateSize(other);
        return Math.toDegrees(angle(other));
    }

    public double distance() {
        return subtract(zero(size)).norm();
    }

    public double distanceTo(@NonNull Vector other) {
        validateSize(other);
        return subtract(other).norm();
    }

    public static @NonNull Vector random(int size, int range) {
        return new Vector(Matrix.random(size,1, 0, range).getColumn(0));
    }

    public static @NonNull Vector intRandom(int size, int range) {
        return new Vector(Matrix.intRandom(size,1, 0, range).getColumn(0));
    }

    public @NonNull List<Vector> canonicalBasis() {
        List<Vector> canonicalVectors = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            double[] base = new double[size];
            base[i] = 1.0;
            canonicalVectors.add(new Vector(base));
        }
        return canonicalVectors;
    }

    public List<Vector> toBasis() {
        List<Vector> basis = new ArrayList<>(size);
        canonicalBasis().forEach(vector -> basis.add(projection(vector)));
        return basis;
    }

    public Vector baseChange(List<Vector> newBasis) {
        double[] data = new double[size];
        for (int i = 0; i < size; i++) {
            double factor = dotProduct(newBasis.get(i)) * Math.pow(newBasis.get(i).norm(), -2);
            data[i] = factor;
        }
        return new Vector(data);
    }

    public ColumnMatrix getCoordinates() {
        return ColumnMatrix.copyOf(coordinates);
    }

    public double get(int index) {
        return coordinates.get(index);
    }

    public boolean isZero() {
        return equals(Vector.zero(size));
    }


    private void validateSize(@NonNull Vector other) {
        if (other.size != size) throw new IllegalArgumentException("Vector size doesn't match");
    }

    private void validateNormalization() {
        if (norm() == 0.0) throw new ArithmeticException("Cannot normalize zero vector");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < size; i++) {
            double val = get(i) == 0.0 ? 0.0 : get(i);
            sb.append(String.format("%.2f", val));
            if (i < size - 1) sb.append(" , ");
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        if (size != vector.size) return false;
        return coordinates.equals(vector.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, coordinates);
    }
}
