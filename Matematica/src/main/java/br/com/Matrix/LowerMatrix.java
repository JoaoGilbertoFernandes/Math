package br.com.Matrix;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

public class LowerMatrix extends TriangularMatrix {

    public LowerMatrix(int size) {
        super(size);
    }

    public LowerMatrix(double[][] data) {
        super(lowerData(data));
    }

    public LowerMatrix(@NonNull Matrix matrix) {
        this(matrix.getData());
    }


    @Override
    public boolean isLower() {
        return true;
    }

    public @NonNull static LowerMatrix zero(int size) {
        return new LowerMatrix(size);
    }

    @Override
    public UpperMatrix transpose() {
        return new UpperMatrix(super.transpose());
    }

    @Override
    public @NotNull LowerMatrix inverse() {
        validateLower(super.inverse());
        return new LowerMatrix(super.inverse());
    }

    public @NonNull LowerMatrix addLower(@NonNull Matrix other) {
        return computeSum(other);
    }

    @Override
    public @NonNull LowerMatrix multiplyBy(@NonNull Matrix other) {
        return computeMultiplication(other);
    }


    private static double @NonNull [][] lowerData(double @NonNull [][] data) {
        int size = data.length;
        double[][] lwData = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                lwData[i][j] = (j > i) ? 0.0 : data[i][j];
            }
        }
        return lwData;
    }


    private @NonNull LowerMatrix computeSum(@NonNull Matrix other) {
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

    private @NonNull LowerMatrix computeMultiplication(@NonNull Matrix other) {
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


    private static void validateLower(@NonNull Matrix matrix) {
        if (!matrix.isLower()) throw new IllegalArgumentException("Matrix is not lower");
    }
}
