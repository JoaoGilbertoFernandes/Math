package br.com.Matrix;

import java.util.Arrays;

public class DiagonalMatrix extends TriangularMatrix {


    public DiagonalMatrix(int size) {
        super(size);
    }

    public DiagonalMatrix(double[] data) {
        super(diagonalData(data));
    }

    public DiagonalMatrix(double[][] data) {
        super(diagonalData(data));
    }

    public DiagonalMatrix(Matrix matrix) {
        this(matrix.getData());
    }


    public static DiagonalMatrix zero(int size) {
        return new DiagonalMatrix(size);
    }

    public DiagonalMatrix addDiagonal(Matrix other) {
        return computeSum(other);
    }

    public LowerMatrix addLower(Matrix other) {
        validateAsLower(other);
        return new LowerMatrix(other.add(this));
    }

    public UpperMatrix addUpper(Matrix other) {
        validateAsUpper(other);
        return new UpperMatrix(other.add(this));
    }

    public DiagonalMatrix multiplyByDiagonal(Matrix other) {
        return computeMultiplication(other);
    }

    public LowerMatrix multiplyByLower(Matrix other) {
        validateAsLower(other);
        return new LowerMatrix(multiply(other));
    }

    public UpperMatrix multiplyByUpper(Matrix other) {
        validateAsUpper(other);
        return new UpperMatrix(multiply(other));
    }

    @Override
    public DiagonalMatrix inverse() {
        return computeInverse();
    }

    @Override
    public boolean isLower() {
        return true;
    }

    @Override
    public boolean isSymmetric() {
        return true;
    }

    @Override
    public boolean isUpper() {
        return true;
    }


    private static double[][] diagonalData(double[] data) {
        int size = data.length;
        double[][] diagonalData = new double[size][size];
        for (int i = 0; i < size; i++) {
            diagonalData[i][i] = data[i];
        }
        return diagonalData;
    }

    private static double[][] diagonalData(double[][] data) {
        int size = data.length;
        double[][] diagonalData = new double[size][size];
        for (int i = 0; i < size; i++) {
            diagonalData[i][i] = data[i][i];
        }
        return diagonalData;
    }

    private DiagonalMatrix computeInverse() {
        double[][] invData = new double[size][size];
        for (int i = 0; i < size; i++) {
            invData[i][i] = Math.pow(data[i][i], -1);
        }
        return new DiagonalMatrix(invData);
    }

    private DiagonalMatrix computeSum(Matrix other) {
        validateAsDiagonal(other);
        DiagonalMatrix dm = new DiagonalMatrix(other);
        if (isZero()) return dm;
        if (dm.isZero()) return this;
        double[][] result = new double[size][size];
        for (int i = 0; i < size; i++) {
                result[i][i] = data[i][i] + dm.data[i][i];
        }
        return new DiagonalMatrix(result);
    }

    private DiagonalMatrix computeMultiplication(Matrix other) {
        validateAsDiagonal(other);
        if (isZero() || other.isZero()) return zero(size);
        double[] result = new double[size];
        for (int i = 0; i < size; i++) {
            result[i] = data[i][i] * other.data[i][i];
        }
        return new DiagonalMatrix(result);
    }

    private static void validateAsUpper(Matrix matrix) {
        if (!matrix.isUpper()) throw new IllegalArgumentException("Matrix is not upper");
    }

    private static void validateAsLower(Matrix matrix) {
        if (!matrix.isLower()) throw new IllegalArgumentException("Matrix is not lower");
    }

    private static void validateAsDiagonal(Matrix matrix) {
        if (!matrix.isDiagonal()) throw new IllegalArgumentException("Matrix is not Diagonal");
        if (matrix.det() == 0.0) throw new ArithmeticException("Matrix is singular.");
    }
}
