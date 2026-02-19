package br.com.Matrix;

import lombok.*;

public class SquareMatrix extends Matrix {

    protected final int size;

    public SquareMatrix(int size) {
        super(size, size);
        this.size = size;
    }

    public SquareMatrix(double @NonNull [][] data) {
        super(squareData(data));
        this.size = data.length;
    }

    public SquareMatrix(@NonNull Matrix matrix) {
        this(matrix.getData());
    }


    @Override
    public boolean isSquare() {
        return true;
    }

    public static @NonNull SquareMatrix zero(int size) {
        return new SquareMatrix(size);
    }

    public @NonNull DiagonalMatrix toIdentity() {
        return identity(size);
    }

    @Override
    public SquareMatrix transpose() {
        return new SquareMatrix(super.transpose());
    }

    @Override
    public @NonNull SquareMatrix add(@NonNull Matrix other) {
        return new SquareMatrix(super.add(other));
    }

    @Override
    public @NonNull SquareMatrix subtract(@NonNull Matrix other) {
        return new SquareMatrix(super.subtract(other));
    }

    @Override
    public @NonNull SquareMatrix multiplyByScalar(double value) {
        return new SquareMatrix(super.multiplyByScalar(value));
    }

    public @NonNull SquareMatrix multiplyBy(@NonNull Matrix other) {
        validateAsSquare(other.getData());
        return new SquareMatrix(super.multiply(other));
    }

    @Override
    public @NonNull SquareMatrix subMatrix(int rows, int cols) {
        return new SquareMatrix(super.subMatrix(rows, cols));
    }

    public int nilpotencyIndex() {
        return computeNilpotency();
    }


    private static double @NonNull [][] squareData(double @NonNull [][] data) {
        validateAsSquare(data);
        int size = data.length;
        double[][] sqrData = new double[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(data[i], 0, sqrData[i], 0, size);
        }
        return sqrData;
    }


    private int computeNilpotency() {
        if (det() != 0) throw new ArithmeticException();
        SquareMatrix zero = zero(size);
        SquareMatrix result = this;
        for (int k = 1; k <= size; k++) {
            if (result.equals(zero)) return k;
            result = result.multiplyBy(this);
        }
        return -1;
    }


    private static void validateAsSquare(double @NonNull [][] data) {
        Matrix matrix = new Matrix(data);
        if (!matrix.isSquare()) throw new IllegalArgumentException("Matrix must be square");
    }
}