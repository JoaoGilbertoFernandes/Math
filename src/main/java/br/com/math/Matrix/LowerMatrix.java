package br.com.math.Matrix;

import org.jetbrains.annotations.NotNull;

public class LowerMatrix extends TriangularMatrix {

    public LowerMatrix(int size) {
        super(size);
    }

    public LowerMatrix(double[][] data) {
        super(lowerData(data));
    }

    public LowerMatrix(Matrix matrix) {
        this(matrix.getData());
    }


    public static LowerMatrix zero(int size) {
        return new LowerMatrix(size);
    }

    public LowerMatrix addLower(Matrix other) {
        return computeSum(other);
    }

    public LowerMatrix multiplyByLower(Matrix other) {
        return computeMultiplication(other);
    }

    @Override
    public @NotNull LowerMatrix inverse() {
        validateLower(super.inverse());
        return new LowerMatrix(super.inverse());
    }

    @Override
    public boolean isLower() {
        return true;
    }

    @Override
    public UpperMatrix transpose() {
        return new UpperMatrix(super.transpose());
    }


    private static double[][] lowerData(double[][] data) {
        int size = data.length;
        double[][] lwData = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                lwData[i][j] = (j > i) ? 0.0 : data[i][j];
            }
        }
        return lwData;
    }

    private LowerMatrix computeSum(Matrix other) {
        validateLower(other);
        LowerMatrix lw = new LowerMatrix(other);
        if (isZero()) return lw;
        if (lw.isZero()) return this;
        double[][] result = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= i; j++) {
                result[i][j] = data[i][j] + lw.data[i][j];
            }
        }
        return new LowerMatrix(result);
    }

    private LowerMatrix computeMultiplication(Matrix other) {
        validateLower(other);
        LowerMatrix lw = new LowerMatrix(other);
        double[][] result = new double[size][size];
        if (other.isZero() || isZero()) return zero(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= i; j++) {
                for (int k = j; k <= i; k++) {
                    result[i][j] += data[i][k] * lw.data[k][j];
                }
            }
        }
        return new LowerMatrix(result);
    }

    private static void validateLower(Matrix matrix) {
        if (!matrix.isLower()) throw new IllegalArgumentException("Matrix is not lower");
    }
}
