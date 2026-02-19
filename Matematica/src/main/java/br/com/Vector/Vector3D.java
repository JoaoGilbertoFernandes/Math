package br.com.Vector;

import br.com.Matrix.Matrix;
import lombok.NonNull;

public class Vector3D extends Vector {

    private static final int SIZE = 3;

    public Vector3D() {
        super(SIZE);
    }

    public Vector3D(double x, double y, double z) {
        super(new double[]{x, y, z});
    }

    @Override
    public @NonNull Vector3D add(@NonNull Vector other) {
        Vector result = super.add(other);
        return new Vector3D(result.get(0), result.get(1), result.get(2));
    }

    @Override
    public @NonNull Vector3D subtract(@NonNull Vector other) {
        Vector result = super.subtract(other);
        return new Vector3D(result.get(0), result.get(1), result.get(2));
    }

    @Override
    public Vector3D multiplyByScalar(double value) {
        Vector result = super.multiplyByScalar(value);
        return new Vector3D(result.get(0), result.get(1), result.get(2));
    }

    public Vector3D normalized() {
        Vector result = super.normalized();
        return new Vector3D(result.get(0), result.get(1), result.get(2));
    }

    public Vector3D crossProduct(@NonNull Vector other) {
        Matrix matrix = outerProduct(other);
        double x = matrix.get(1,2) - matrix.get(2,1);
        double y = matrix.get(2,0) - matrix.get(0,2);
        double z  = matrix.get(0,1) - matrix.get(1,0);
        return new Vector3D(x, y, z);
    }

    public static @NonNull Vector3D random(int range) {
        Vector result = random(SIZE, range);
        return new Vector3D(result.get(0), result.get(1), result.get(2));
    }

    public static @NonNull Vector3D intRandom(int range) {
        Vector result = intRandom(SIZE, range);
        return new Vector3D(result.get(0), result.get(1), result.get(2));
    }

    public @NonNull SphericalCoordinates toSpherical() {
        double theta = Math.acos(get(2) / norm());
        double phi = Math.atan2(get(1), get(0));
        return new SphericalCoordinates(norm(), theta, phi);
    }

    public @NonNull CylindricalCoordinates toCylindrical() {
        double r = new Vector3D(get(0), get(1), 0).norm();
        double theta = Math.atan2(get(1), get(0));
        return  new CylindricalCoordinates(r, theta, get(2));
    }

    @Override
    public @NonNull Vector3D projection(@NonNull Vector other) {
        Vector result = super.projection(other);
        return new Vector3D(result.get(0), result.get(1), result.get(2));
    }

    @Override
    public @NonNull Vector3D rejection(@NonNull Vector other) {
        Vector result = super.rejection(other);
        return new Vector3D(result.get(0), result.get(1), result.get(2));
    }
}
