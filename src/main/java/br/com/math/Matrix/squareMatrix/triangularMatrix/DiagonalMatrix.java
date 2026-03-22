package br.com.math.matrix.squareMatrix.triangularMatrix;

import br.com.math.matrix.Matrix;

import static br.com.math.matrix.squareMatrix.triangularMatrix.TriangularType.DIAGONAL;

public class DiagonalMatrix extends TriangularMatrix {

    public DiagonalMatrix(int size) {
        super(size, DIAGONAL);
    }

    public DiagonalMatrix(double ... data) {
        int size = data.length;
        double[][] superData = new double[size][size];
        for (int i = 0; i < data.length; i++) {
            superData[i][i] = data[i];
        }
        this(superData);
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
