package br.com.math.vector;

import br.com.math.matrix.ColumnMatrix;
import br.com.math.matrix.Matrix;
import br.com.math.matrix.VectorMatrix;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Vector {

    protected final int size;
    protected final ColumnMatrix coordinates;

    public Vector(int size) {
        this.size = size;
        coordinates = new ColumnMatrix(size);
    }

    public Vector(double[] data) {
        size = data.length;
        coordinates = new ColumnMatrix(data);
    }

    public Vector(VectorMatrix coordinates) {
        size = coordinates.getSize();
        this.coordinates = new ColumnMatrix(coordinates.getCol(0));
    }


    public static Vector zero(int size) {
        return new Vector(size);
    }

    public static Vector unit(int size) {
        double[] data = new double[size];
        Arrays.fill(data, 1.0);
        return new Vector(data);
    }

    public Vector multiply(double value) {
        return new Vector(coordinates.multiply(value));
    }

    public Vector add(Vector other) {
        return new Vector(coordinates.add(other.coordinates));
    }

    public Vector subtract(Vector other) {
        return add(other.multiply(-1));
    }

    public double dotProduct(Vector other) {
        validateSize(other);
        return coordinates.multiplyByVector(other.coordinates.transpose());
    }

    public Matrix outerProduct(Vector other) {
        return coordinates.multiply(other.coordinates.transpose());
    }

    public Vector projection(Vector other) {
        return other.normalized().multiply(dotProduct(other.normalized()));
    }

    public Vector rejection(Vector other) {
        return subtract(projection(other));
    }

    public Decomposition decompose(Vector other) {
        return new Decomposition(projection(other), rejection(other));
    }

    public double norm() {
        return Math.sqrt(dotProduct(this));
    }

    public Vector normalized() {
        validateNormalization();
        return multiply(1 / norm());
    }

    public double cosSimilarity(Vector other) {
        validateSize(other);
        if (norm() == 0 || other.norm() == 0) return 0.0;
        double value = dotProduct(other) * 1 / (norm() * other.norm());
        return Math.max(-1.0, Math.min(1.0, value));
    }

    public double angle(Vector other) {
        validateSize(other);
        return Math.acos(cosSimilarity(other));
    }

    public double angleDeg(Vector other) {
        validateSize(other);
        return Math.toDegrees(angle(other));
    }

    public double distance(Vector other) {
        validateSize(other);
        return subtract(other).norm();
    }

    public static Vector random(int size, int range) {
        return new Vector(Matrix.random(size,1, 0, range).getCol(0));
    }

    public static Vector intRandom(int size, int range) {
        return new Vector(Matrix.intRandom(size,1, 0, range).getCol(0));
    }

    public List<Vector> canonicalBasis() {
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

    public VectorMatrix getCoordinates() {
        return VectorMatrix.copyOf(coordinates, coordinates.getType());
    }

    public double get(int index) {
        return coordinates.get(index);
    }

    public int getSize() {
        return size;
    }

    public boolean isZero() {
        return equals(Vector.zero(size));
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
        if (!(o instanceof Vector v)) return false;
        if (size != v.size) return false;
        for (int i = 0; i < size; i++) {
            BigDecimal a = BigDecimal.valueOf(get(i));
            BigDecimal b = BigDecimal.valueOf(v.get(i));
            if (a.compareTo(b) != 0.0) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, coordinates);
    }




    private void validateSize(Vector other) {
        if (other.size != size) throw new IllegalArgumentException("Vector size doesn't match");
    }

    private void validateNormalization() {
        if (norm() == 0.0) throw new ArithmeticException("Cannot normalize zero vector");
    }
}
