package br.com.math.matrix.squareMatrix.triangularMatrix;

import br.com.math.matrix.*;
import br.com.math.matrix.squareMatrix.SquareMatrix;

import static br.com.math.matrix.squareMatrix.triangularMatrix.TriangularType.*;

public abstract class TriangularMatrix extends SquareMatrix {

    private final TriangularType type;

    public TriangularMatrix(int size, TriangularType type) {
        super(size);
        this.type = type;
    }

    public TriangularMatrix(double[][] data, TriangularType type) {
        super(triangularData(data, type));
        this.type = type;
    }

    public TriangularMatrix(Matrix matrix, TriangularType type) {
        this(matrix.getData(), type);
    }

    @Override
    public boolean isDiagonal() {
        return type == DIAGONAL;
    }

    @Override
    public boolean isLower() {
        return type == LOWER;
    }

    @Override
    public boolean isSymmetric() {
        if (type == DIAGONAL) return true;
        return super.isSymmetric();
    }

    @Override
    public boolean isTriangular() {
        return true;
    }

    @Override
    public boolean isUpper() {
        return type == UPPER;
    }

    @Override
    public TriangularMatrix inverse() {
        return switch (type) {
            case DIAGONAL -> new DiagonalMatrix(diagonalInverseData());
            case LOWER -> new LowerMatrix(super.inverse());
            case UPPER -> new UpperMatrix(super.inverse());
        };
    }

    @Override
    public TriangularMatrix transpose() {
        return switch (type) {
            case DIAGONAL -> new DiagonalMatrix(super.transpose());
            case LOWER -> new UpperMatrix(super.transpose());
            case UPPER -> new LowerMatrix(super.transpose());
        };
    }

    @Override
    public double det() {
        return computeDeterminant();
    }



    /** PRIVATE METHODS */

    private static double[][] triangularData(double[][] data, TriangularType type) {
        int size = data.length;
        double[][] result = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = type.validatePosition(i, j) ? data[i][j] : 0.0;
            }
        }
        return result;
    }

    private double computeDeterminant() {
        double det = 1.0;
        for (int i = 0; i < getRows(); i++) {
            det *= getData()[i][i];
        }
        return det;
    }

    private double[][] diagonalInverseData() {
        if (computeDeterminant() == 0.0) throw new IllegalArgumentException("Matrix is not invertible.");
        double[][] invData = new double[size][size];
        for (int i = 0; i < size; i++) {
            invData[i][i] = 1 / getData()[i][i];
        }
        return invData;
    }
}
