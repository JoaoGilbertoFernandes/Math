package br.com.math.matrix;

import static br.com.math.matrix.TriangularType.DIAGONAL;

public class DiagonalMatrix extends TriangularMatrix {


    public DiagonalMatrix(int size) {
        super(size, DIAGONAL);
    }

    public DiagonalMatrix(double[][] data) {
        super(data, DIAGONAL);
    }

    public DiagonalMatrix(Matrix matrix) {
        super(matrix, DIAGONAL);
    }

    @Override
    public boolean isDiagonal() {
        return true;
    }

    @Override
    public boolean isLower() {
        return true;
    }

    @Override
    public boolean isSymmetric() {
        return true;
    }

    @Override
    public boolean isUpper() {
        return true;
    }
}
