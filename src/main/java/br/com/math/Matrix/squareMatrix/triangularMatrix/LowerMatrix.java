package br.com.math.matrix.squareMatrix.triangularMatrix;

import br.com.math.matrix.Matrix;

import static br.com.math.matrix.squareMatrix.triangularMatrix.TriangularType.LOWER;

public class LowerMatrix extends TriangularMatrix {

    public LowerMatrix(double[][] data) {
        super(data, LOWER);
    }

    public LowerMatrix(Matrix matrix) {
        super(matrix, LOWER);
    }

    @Override
    public boolean isLower() {
        return true;
    }
}
