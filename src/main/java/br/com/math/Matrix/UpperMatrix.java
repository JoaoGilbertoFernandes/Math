package br.com.math.matrix;

import static br.com.math.matrix.TriangularType.UPPER;

public class UpperMatrix extends TriangularMatrix {

    public UpperMatrix(double[][] data) {
        super(data, UPPER);
    }

    public UpperMatrix(Matrix matrix) {
        super(matrix, UPPER);
    }

    @Override
    public boolean isUpper() {
        return true;
    }
}
