package br.com.Vector;

import lombok.Getter;
import lombok.NonNull;

public class Vector2D extends Vector {

    @Getter
    private static final int SIZE = 2;

    public Vector2D() {
        super(SIZE);
    }

    public Vector2D(double x, double y) {
        super(new double[]{x, y});
    }

    @Override
    public @NonNull Vector2D add(@NonNull Vector other) {
        Vector result = super.add(other);
        return new Vector2D(result.get(0), result.get(1));
    }

    @Override
    public @NonNull Vector2D subtract(@NonNull Vector other) {
        Vector result = super.subtract(other);
        return new Vector2D(result.get(0), result.get(1));
    }

    @Override
    public Vector2D multiplyByScalar(double value) {
        Vector result = super.multiplyByScalar(value);
        return new Vector2D(result.get(0), result.get(1));
    }

    @Override
    public Vector2D normalized() {
        Vector result = super.normalized();
        return new Vector2D(result.get(0), result.get(1));
    }

    public static @NonNull Vector2D random(int range) {
        Vector result = random(SIZE, range);
        return new Vector2D(result.get(0), result.get(1));
    }

    public static @NonNull Vector2D intRandom(int range) {
        Vector result = intRandom(SIZE, range);
        return new Vector2D(result.get(0), result.get(1));
    }

    public @NonNull PolarCoordinates toPolar() {
        double theta = Math.acos(get(0) / norm());
        return new PolarCoordinates(norm(), angle(canonicalBasis().getFirst()));
    }

    @Override
    public @NonNull Vector2D projection(@NonNull Vector other) {
        Vector result = super.projection(other);
        return new Vector2D(result.get(0), result.get(1));
    }

    @Override
    public @NonNull Vector2D rejection(@NonNull Vector other) {
        Vector result = super.rejection(other);
        return new Vector2D(result.get(0), result.get(1));
    }
}
