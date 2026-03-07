package br.com.math.matrix;

import java.util.Arrays;

public class RowMatrix extends Matrix {

    private final int size;
    private static final int ROWS = 1;
    private static final int ROW = 0;

    public RowMatrix(int size) {
        super(ROWS, size);
        this.size = size;
    }

    public RowMatrix(double[] data) {
        super(ROWS, data.length);
        size = data.length;
        this.data[ROW] = Arrays.copyOf(data, size);
    }


    public static RowMatrix copyOf(Matrix matrix) {
        return new RowMatrix(matrix.getRow(ROW));
    }

    public static RowMatrix copyOf(Matrix matrix, int row) {
        return new RowMatrix(matrix.getRow(row));
    }

    public static RowMatrix zero(int size) {
        return new RowMatrix(size);
    }

    public double get(int col) {
        return getCol(col)[0];
    }

    public int getSize() {
        return size;
    }

    public double multiplyByCol(Matrix other) {
        validateAsColumn(other);
        return multiply(other).get(ROW, ROW);
    }

    @Override
    public RowMatrix add(Matrix other) {
        return copyOf(super.add(other));
    }

    @Override
    public boolean isRow() {
        return true;
    }

    @Override
    public RowMatrix multiplyByScalar(double value) {
        return copyOf(super.multiplyByScalar(value));
    }

    @Override
    public ColumnMatrix transpose() {
        return ColumnMatrix.copyOf(super.transpose());
    }


    private void validateAsColumn(Matrix matrix) {
        if (!matrix.isColumn()) throw new IllegalArgumentException("Matrix is not column");
    }
}
