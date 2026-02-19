package br.com.Matrix;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

public class UpperMatrix extends TriangularMatrix {

    public UpperMatrix(int size) {
        super(size);
    }

    public UpperMatrix(double @NonNull [][] data) {
        super(upperData(data));
    }

    public UpperMatrix(@NonNull Matrix matrix) {
        this(matrix.getData());
    }


    public @NonNull static UpperMatrix zero(int size) {
        return new UpperMatrix(size);
    }

    @Override
    public LowerMatrix transpose() {
        return new LowerMatrix(super.transpose());
    }

    @Override
    public @NotNull UpperMatrix inverse() {
        validateUpper(super.inverse());
        return new UpperMatrix(super.inverse());
    }

    @Override
    public @NonNull UpperMatrix multiplyBy(@NonNull Matrix other) {
        return computeMultiplication(other);
    }

    public @NonNull UpperMatrix addUpper(@NonNull Matrix other) {
        return computeSum(other);
    }

    @Override
    public boolean isUpper() {
        return true;
    }


    private static double @NonNull [][] upperData(double @NonNull [][] data) {
        int size = data.length;
        double[][] upData = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                upData[i][j] = (j < i) ? 0.0 : data[i][j];
            }
        }
        return upData;
    }


    private @NonNull UpperMatrix computeSum(@NonNull Matrix other) {
        validateUpper(other);
        UpperMatrix upp = new UpperMatrix(other);
        if (isZero()) return upp;
        if (upp.isZero()) return this;
        double[][] result = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                result[i][j] = data[i][j] + upp.data[i][j];
            }
        }
        return new UpperMatrix(result);
    }

    private @NonNull UpperMatrix computeMultiplication(@NonNull Matrix other) {
        validateUpper(other);
        UpperMatrix upp = new UpperMatrix(other);
        double[][] result = new double[size][size];
        if (other.isZero() || isZero()) return zero(size);
        for (int i = 0; i < size; i++) {
            for (int j = i; j < upp.size; j++) {
                for (int k = i; k <= j; k++) {
                    result[i][j] += data[i][k] * upp.data[k][j];
                }
            }
        }
        return new UpperMatrix(result);
    }


    private static void validateUpper(@NonNull Matrix matrix) {
        if (!matrix.isUpper()) throw new IllegalArgumentException("Matrix is not upper");
    }
}
