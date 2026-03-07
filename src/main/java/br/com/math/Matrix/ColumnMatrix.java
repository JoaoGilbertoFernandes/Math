package br.com.math.matrix;

import java.util.Arrays;

public class ColumnMatrix extends Matrix {

    private final int size;
    private static final int COLS = 1;
    private static final int COL = 0;

    public ColumnMatrix(int size) {
        super(size, COLS);
        this.size = size;
    }

    public ColumnMatrix(double[] data) {
        super(colData(data));
        size = data.length;
    }


    public static ColumnMatrix copyOf(Matrix matrix) {
        return copyOf(matrix, COL);
    }

    public static ColumnMatrix copyOf(Matrix matrix, int col) {
        double[] data = Arrays.copyOf(matrix.getCol(col), matrix.rows);
        return new ColumnMatrix(data);
    }

    public static ColumnMatrix zero(int size) {
        return new ColumnMatrix(size);
    }

    public double get(int index) {
        return getRow(index)[0];
    }

    public int getSize() {
        return size;
    }
    public void set(int index, double value) {}

    public double multiplyByRow(Matrix other) {
        validateAsRow(other);
        return other.multiply(this).get(COL, COL);
    }

    @Override
    public ColumnMatrix add(Matrix other) {
        return ColumnMatrix.copyOf(super.add(other));
    }

    @Override
    public boolean isColumn() {
        return true;
    }

    @Override
    public ColumnMatrix multiplyByScalar(double value) {
        return copyOf(super.multiplyByScalar(value));
    }

    @Override
    public RowMatrix transpose() {
        return RowMatrix.copyOf(super.transpose());
    }


    private static double[][] colData(double[] data) {
        int size = data.length;
        double[][] colData = new double[size][COLS];
        for (int j = 0; j < data.length; j++) {
            colData[j][COL] = data[j];
        }
        return colData;
    }

    private void validateAsRow(Matrix matrix) {
        if (!matrix.isRow()) throw new IllegalArgumentException("Matrix is not row");
    }
}
