package br.com.math.matrix;

import static br.com.math.matrix.TriangularType.LOWER;

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
