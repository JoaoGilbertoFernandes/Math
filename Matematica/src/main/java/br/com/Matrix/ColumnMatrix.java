package br.com.Matrix;

import lombok.Getter;
import lombok.NonNull;

import java.util.Arrays;

public class ColumnMatrix extends Matrix {

    @Getter
    private final int size;
    private static final int COLS = 1;
    private static final int COL = 0;

    public ColumnMatrix(int size) {
        super(size, COLS);
        this.size = size;
    }

    public ColumnMatrix(double @NonNull [] data) {
        super(columnData(data));
        size = data.length;
    }

    @Override
    public boolean isColumn() {
        return true;
    }

    public static @NonNull ColumnMatrix zero(int size) {
        return new ColumnMatrix(size);
    }

    public static @NonNull ColumnMatrix copyOf(@NonNull Matrix matrix) {
        double[] data = Arrays.copyOf(matrix.getColumn(COL), matrix.rows);
        return new ColumnMatrix(data);
    }

    public static @NonNull ColumnMatrix copyOf(@NonNull Matrix matrix, int col) {
        double[] data = Arrays.copyOf(matrix.getColumn(col), matrix.rows);
        return new ColumnMatrix(data);
    }

    @Override
    public @NonNull RowMatrix transpose() {
        return RowMatrix.copyOf(super.transpose());
    }

    @Override
    public @NonNull ColumnMatrix add(@NonNull Matrix other) {
        return ColumnMatrix.copyOf(super.add(other));
    }

    @Override
    public @NonNull ColumnMatrix multiplyByScalar(double value) {
        return copyOf(super.multiplyByScalar(value));
    }

    public double multiplyBy(@NonNull Matrix other) {
        validateAsRow(other);
        return other.multiply(this).get(COL, COL);
    }

    public double get(int index) {
        return getRow(index)[0];
    }

    private static double @NonNull [][] columnData(double @NonNull [] data) {
        int size = data.length;
        double[][] colData = new double[size][COLS];
        for (int j = 0; j < data.length; j++) {
            colData[j][COL] = data[j];
        }
        return colData;
    }

    private void validateAsRow(@NonNull Matrix matrix) {
        if (!matrix.isRow()) throw new IllegalArgumentException("Matrix is not row");
    }
}
