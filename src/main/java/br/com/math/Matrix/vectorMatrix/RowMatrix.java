package br.com.math.matrix.vectorMatrix;

import java.util.Objects;

import static br.com.math.matrix.vectorMatrix.VectorType.ROW;

public class RowMatrix extends VectorMatrix {

    public RowMatrix(int size) {
        super(new double[size], ROW);
    }

    public RowMatrix(double[] data) {
        super(Objects.requireNonNull(data), ROW);
    }

    @Override
    public boolean isRow() {
        return true;
    }

    public double[] getRow() {
        return super.getRow(0);
    }
}
