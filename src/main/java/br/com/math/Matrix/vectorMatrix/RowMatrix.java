package br.com.math.matrix.vectorMatrix;

import java.util.Objects;

import static br.com.math.matrix.vectorMatrix.VectorType.ROW;

public class RowMatrix extends VectorMatrix {

    public RowMatrix(int size) {
        super(ROW, new double[size]);
    }

    public RowMatrix(double[] data) {
        super(ROW, Objects.requireNonNull(data));
    }

    @Override
    public boolean isRow() {
        return true;
    }

    public double[] getRow() {
        return super.getRow(0);
    }
}
