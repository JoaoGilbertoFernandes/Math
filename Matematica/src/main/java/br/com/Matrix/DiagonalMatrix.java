package br.com.Matrix;

import lombok.NonNull;

public class DiagonalMatrix extends TriangularMatrix {


    public DiagonalMatrix(int size) {
        super(size);
    }

    public DiagonalMatrix(double @NonNull [] data) {
        super(diagonalData(data));
    }

    public DiagonalMatrix(double @NonNull [][] data) {
        super(diagonalData(data));
    }

    public DiagonalMatrix(@NonNull Matrix matrix) {
        this(matrix.getData());
    }


    public static @NonNull DiagonalMatrix zero(int size) {
        return new DiagonalMatrix(size);
    }

    @Override
    public @NonNull DiagonalMatrix inverse() {
        return computeInverse();
    }

    @Override
    public boolean isUpper() {
        return true;
    }

    @Override
    public boolean isLower() {
        return true;
    }

    @Override
    public boolean isSymmetric() {
        return true;
    }


    public @NonNull UpperMatrix addUpper(@NonNull Matrix other) {
        return computeUpperSum(other);
    }

    public @NonNull LowerMatrix addLower(@NonNull Matrix other) {
        return computeLowerSum(other);
    }

    public @NonNull DiagonalMatrix addDiagonal(@NonNull Matrix other) {
        return computeSum(other);
    }

    public UpperMatrix multiplyByUpper(@NonNull Matrix other) {
        return computeUpperMultiplication(other);
    }

    public LowerMatrix multiplyByLower(@NonNull Matrix other) {
        return computeLowerMultiplication(other);
    }

    public DiagonalMatrix multiplyByDiagonal(Matrix other) {
        return computeMultiplication(other);
    }


    private static double @NonNull [][] diagonalData(double @NonNull [] data) {
        int size = data.length;
        double[][] diagonalData = new double[size][size];
        for (int i = 0; i < size; i++) {
            diagonalData[i][i] = data[i];
        }
        return diagonalData;
    }

    private static double @NonNull [][] diagonalData(double @NonNull [][] data) {
        int size = data.length;
        double[][] diagonalData = new double[size][size];
        for (int i = 0; i < size; i++) {
            diagonalData[i][i] = data[i][i];
        }
        return diagonalData;
    }


    private @NonNull DiagonalMatrix computeInverse() {
        validateDiagonal(this);
        double[][] invData = new double[size][size];
        for (int i = 0; i < size; i++) {
            invData[i][i] = Math.pow(data[i][i], -1);
        }
        return new DiagonalMatrix(invData);
    }

    private @NonNull DiagonalMatrix computeSum(@NonNull Matrix other) {
        validateDiagonal(other);
        DiagonalMatrix upp = new DiagonalMatrix(other);
        if (isZero()) return upp;
        if (upp.isZero()) return this;
        double[][] result = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                result[i][j] = data[i][j] + upp.data[i][j];
            }
        }
        return new DiagonalMatrix(result);
    }

    private @NonNull UpperMatrix computeUpperSum(@NonNull Matrix other) {
        validateUpper(other);
        UpperMatrix upp = new UpperMatrix(other);
        if (isZero()) return upp;
        if (upp.isZero()) return new UpperMatrix(this);
        double[][] result = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                result[i][j] = data[i][j] + upp.data[i][j];
            }
        }
        return new UpperMatrix(result);
    }

    private @NonNull LowerMatrix computeLowerSum(@NonNull Matrix other) {
        validateLower(other);
        LowerMatrix lw = new LowerMatrix(other);
        if (isZero()) return lw;
        if (lw.isZero()) return new LowerMatrix(this);
        double[][] result = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= i; j++) {
                result[i][j] = data[i][j] + lw.data[i][j];
            }
        }
        return new LowerMatrix(result);
    }

    private @NonNull DiagonalMatrix computeMultiplication(@NonNull Matrix other) {
        validateDiagonal(other);
        if (isZero() || other.isZero()) return zero(size);
        double[] result = new double[size];
        for (int i = 0; i < size; i++) {
            result[i] = data[i][i] * other.data[i][i];
        }
        return new DiagonalMatrix(result);
    }

    private @NonNull UpperMatrix computeUpperMultiplication(@NonNull Matrix other) {
        validateUpper(other);
        UpperMatrix upp = new UpperMatrix(other);
        double[][] result = new double[size][size];
        if (other.isZero() || isZero()) return new UpperMatrix(size);
        for (int i = 0; i < size; i++) {
            for (int j = i; j < upp.size; j++) {
                for (int k = i; k <= j; k++) {
                    result[i][j] += data[i][k] * upp.data[k][j];
                }
            }
        }
        return new UpperMatrix(result);
    }

    private @NonNull LowerMatrix computeLowerMultiplication(@NonNull Matrix other) {
        validateLower(other);
        LowerMatrix lw = new LowerMatrix(other);
        double[][] result = new double[size][size];
        if (other.isZero() || isZero()) return new LowerMatrix(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= i; j++) {
                for (int k = j; k <= i; k++) {
                    result[i][j] += data[i][k] * lw.data[k][j];
                }
            }
        }
        return new LowerMatrix(result);
    }


    private static void validateUpper(@NonNull Matrix matrix) {
        if (!matrix.isUpper()) throw new IllegalArgumentException("Matrix is not upper");
    }

    private static void validateLower(@NonNull Matrix matrix) {
        if (!matrix.isLower()) throw new IllegalArgumentException("Matrix is not lower");
    }

    private static void validateDiagonal(@NonNull Matrix matrix) {
        if (!matrix.isDiagonal()) throw new IllegalArgumentException("Matrix is not Diagonal");
        if (matrix.det() == 0.0) throw new ArithmeticException("Matrix is singular.");
    }
}
