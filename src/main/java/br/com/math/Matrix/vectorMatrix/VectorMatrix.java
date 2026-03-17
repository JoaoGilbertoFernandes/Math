package br.com.math.matrix.vectorMatrix;

import br.com.math.matrix.Matrix;

import static br.com.math.matrix.vectorMatrix.VectorType.*;

public abstract class VectorMatrix extends Matrix {

    private final int size;
    private final VectorType type;

    public VectorMatrix(double[] data, VectorType type) {
        super(vectorData(data, type));
        size = data.length;
        this.type = type;
    }

    public static VectorMatrix zero(int size, VectorType type) {
        return switch (type) {
            case ROW -> new RowMatrix(size);
            case COLUMN -> new ColumnMatrix(size);
        };
    }

    public static VectorMatrix copyOf(Matrix matrix, VectorType type) {
        return switch (type) {
            case ROW -> {
                validateAsRow(matrix);
                yield copyOf(matrix, ROW,0);
            }
            case COLUMN -> {
                validateAsColumn(matrix);
                yield copyOf(matrix, COLUMN,0);
            }
        };
    }

    public static VectorMatrix copyOf(Matrix matrix, VectorType type, int line) {
        return switch (type) {
            case ROW -> new RowMatrix(matrix.getRow(line));
            case COLUMN -> new ColumnMatrix(matrix.getCol(line));
        };
    }

    @Override
    public VectorMatrix add(Matrix other) {
        return switch (type) {
            case ROW -> {
                validateAsRow(other);
                yield copyOf(super.add(other), ROW,0);
            }
            case COLUMN -> {
                validateAsColumn(other);
                yield copyOf(super.add(other), COLUMN,0);
            }
        };
    }

    public double get(int index) {
        return type == ROW ? getCol(index)[0] : getRow(index)[0];
    }

    public int size() {
        return size;
    }

    public VectorType type() {
        return type;
    }

    @Override
    public boolean isColumn() {
        return type == COLUMN;
    }

    @Override
    public boolean isDiagonal() {
        return false;
    }

    @Override
    public boolean isLower() {
        return false;
    }

    @Override
    public boolean isRow() {
        return type == ROW;
    }

    @Override
    public boolean isSquare() {
        return false;
    }

    @Override
    public boolean isSymmetric() {
        return false;
    }

    @Override
    public boolean isTriangular() {
        return false;
    }

    @Override
    public boolean isUpper() {
        return false;
    }

    @Override
    public VectorMatrix multiply(double value) {
        return switch (type) {
            case ROW -> copyOf(super.multiply(value), ROW,0);
            case COLUMN -> copyOf(super.multiply(value), COLUMN,0);
        };
    }

    public double multiplyByVector(Matrix other) {
        return switch (type) {
            case ROW -> {
                validateAsColumn(other);
                yield multiply(other).get(0, 0);
            }
            case COLUMN -> {
                validateAsRow(other);
                yield other.multiply(this).get(0, 0);
            }
        };
    }

    @Override
    public VectorMatrix transpose() {
        return switch (type) {
            case ROW -> copyOf(super.transpose(), COLUMN,0);
            case COLUMN -> copyOf(super.transpose(), ROW,0);
        };
    }



    private static double[][] vectorData(double[] data, VectorType type) {
        int size = data.length;
        if (type == ROW) {
            double[][] rowData = new double[1][size];
            System.arraycopy(data, 0, rowData[0], 0, size);
            return rowData;
        } else {
            double[][] colData = new double[size][1];
            for (int i = 0; i < size; i++) {
                colData[i][0] = data[i];
            }
            return colData;
        }
    }

    private static void validateAsRow(Matrix matrix) {
        if (!matrix.isRow()) throw new IllegalArgumentException("Matrix is not row");
    }

    private static void validateAsColumn(Matrix matrix) {
        if (!matrix.isColumn()) throw new IllegalArgumentException("Matrix is not column");
    }
}
