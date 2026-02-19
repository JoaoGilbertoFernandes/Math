package br.com.Matrix;

import lombok.Getter;
import lombok.NonNull;

import java.util.Arrays;

public class RowMatrix extends Matrix {

    @Getter
    private final int size;
    private static final int ROWS = 1;
    private static final int ROW = 0;

    public RowMatrix(int size) {
        super(ROWS, size);
        this.size = size;
    }

    public RowMatrix(double @NonNull [] data) {
        super(ROWS, data.length);
        size = data.length;
        this.data[ROW] = Arrays.copyOf(data, size);
    }

    @Override
    public boolean isRow() {
        return true;
    }

    public static @NonNull RowMatrix zero(int size) {
        return new RowMatrix(size);
    }

    public static @NonNull RowMatrix copyOf(@NonNull Matrix matrix) {
        return new RowMatrix(matrix.getRow(ROW));
    }

    public static @NonNull RowMatrix copyOf(@NonNull Matrix matrix, int row) {
        return new RowMatrix(matrix.getRow(row));
    }

    @Override
    public @NonNull ColumnMatrix transpose() {
        return ColumnMatrix.copyOf(super.transpose());
    }

    @Override
    public @NonNull RowMatrix add(@NonNull Matrix other) {
        return copyOf(super.add(other));
    }


    public double multiplyBy(@NonNull Matrix other) {
        validateAsColumn(other);
        return multiply(other).get(ROW, ROW);
    }

    @Override
    public @NonNull RowMatrix multiplyByScalar(double value) {
        return copyOf(super.multiplyByScalar(value));
    }

    public double get(int col) {
        return getColumn(col)[0];
    }

    private void validateAsColumn(@NonNull Matrix matrix) {
        if (!matrix.isColumn()) throw new IllegalArgumentException("Matrix is not column");
    }
}
