package br.com.math.Vector;


public class Vector2D extends Vector {

    private static final int SIZE = 2;

    public Vector2D() {
        super(SIZE);
    }

    public Vector2D(double x, double y) {
        super(new double[]{x, y});
    }

    @Override
    public Vector2D add(Vector other) {
        Vector result = super.add(other);
        return new Vector2D(result.get(0), result.get(1));
    }

    @Override
    public Vector2D subtract(Vector other) {
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

    public static Vector2D random(int range) {
        Vector result = random(SIZE, range);
        return new Vector2D(result.get(0), result.get(1));
    }

    public static Vector2D intRandom(int range) {
        Vector result = intRandom(SIZE, range);
        return new Vector2D(result.get(0), result.get(1));
    }

    public PolarCoordinates toPolar() {
        double theta = Math.acos(get(0) / norm());
        return new PolarCoordinates(norm(), theta);
    }

    @Override
    public Vector2D projection(Vector other) {
        Vector result = super.projection(other);
        return new Vector2D(result.get(0), result.get(1));
    }

    @Override
    public Vector2D rejection(Vector other) {
        Vector result = super.rejection(other);
        return new Vector2D(result.get(0), result.get(1));
    }
}
