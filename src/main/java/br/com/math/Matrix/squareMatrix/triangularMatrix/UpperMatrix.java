package br.com.math.matrix.squareMatrix.triangularMatrix;

import br.com.math.matrix.Matrix;

import static br.com.math.matrix.squareMatrix.triangularMatrix.TriangularType.UPPER;

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
