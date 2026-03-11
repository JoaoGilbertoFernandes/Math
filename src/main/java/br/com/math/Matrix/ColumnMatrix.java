package br.com.math.matrix;

import java.util.Objects;

import static br.com.math.matrix.VectorType.COLUMN;

public class ColumnMatrix extends VectorMatrix {

    public ColumnMatrix(int size) {
        super(new double[size], COLUMN);
    }

    public ColumnMatrix(double[] data) {
        super(Objects.requireNonNull(data), COLUMN);
    }

    @Override
    public boolean isColumn() {
        return true;
    }

    public double[] getCol() {
        return super.getCol(0);
    }
}
