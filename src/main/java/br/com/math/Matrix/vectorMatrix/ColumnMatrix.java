package br.com.math.matrix.vectorMatrix;

import java.util.Objects;

import static br.com.math.matrix.vectorMatrix.VectorType.COLUMN;

public class ColumnMatrix extends VectorMatrix {

    public ColumnMatrix(int size) {
        super(COLUMN, new double[size]);
    }

    public ColumnMatrix(double ... data) {
        super(COLUMN, Objects.requireNonNull(data));
    }

    @Override
    public boolean isColumn() {
        return true;
    }

    public double[] getCol() {
        return super.getCol(0);
    }
}
