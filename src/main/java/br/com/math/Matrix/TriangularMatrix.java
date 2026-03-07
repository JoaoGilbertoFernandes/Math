package br.com.math.matrix;

public abstract class TriangularMatrix extends SquareMatrix {

    public TriangularMatrix(int size) {
        super(size);
    }

    public TriangularMatrix(double[][] data) {
        super(data);
    }


    @Override
    public double det() {
        return computeDeterminant();
    }


    private double computeDeterminant() {
        double det = 1.0;
        for (int i = 0; i < rows; i++) {
            det *= data[i][i];
        }
        return det;
    }
}
