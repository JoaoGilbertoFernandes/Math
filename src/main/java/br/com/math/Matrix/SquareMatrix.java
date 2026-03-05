package br.com.math.Matrix;

public class SquareMatrix extends Matrix {

    protected final int size;

    public SquareMatrix(int size) {
        super(size, size);
        this.size = size;
    }

    public SquareMatrix(double[][] data) {
        super(squareData(data));
        this.size = data.length;
    }

    public SquareMatrix(Matrix matrix) {
        this(matrix.getData());
    }


    public static SquareMatrix zero(int size) {
        return new SquareMatrix(size);
    }

    public int getSize() {
        return size;
    }

    public SquareMatrix multiplyBySquare(Matrix other) {
        validateAsSquare(other.getData());
        return new SquareMatrix(super.multiply(other));
    }

    public int nilpotencyIndex() {
        return computeNilpotency();
    }

    public DiagonalMatrix toIdentity() {
        return identity(size);
    }

    @Override
    public SquareMatrix add(Matrix other) {
        return new SquareMatrix(super.add(other));
    }

    @Override
    public boolean isSquare() {
        return true;
    }

    @Override
    public SquareMatrix multiplyByScalar(double value) {
        return new SquareMatrix(super.multiplyByScalar(value));
    }

    @Override
    public SquareMatrix subMatrix(int rows, int cols) {
        return new SquareMatrix(super.subMatrix(rows, cols));
    }

    @Override
    public SquareMatrix subtract(Matrix other) {
        return new SquareMatrix(super.subtract(other));
    }

    @Override
    public SquareMatrix transpose() {
        return new SquareMatrix(super.transpose());
    }


    /** PRIVATE METHODS */

    private static double[][] squareData(double[][] data) {
        validateAsSquare(data);
        int size = data.length;
        double[][] sqrData = new double[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(data[i], 0, sqrData[i], 0, size);
        }
        return sqrData;
    }

    private int computeNilpotency() {
        if (det() != 0) throw new ArithmeticException();
        SquareMatrix zero = zero(size);
        SquareMatrix result = this;
        for (int k = 1; k <= size; k++) {
            if (result.equals(zero)) return k;
            result = result.multiplyBySquare(this);
        }
        return -1;
    }

    private static void validateAsSquare(double[][] data) {
        Matrix matrix = new Matrix(data);
        if (!matrix.isSquare()) throw new IllegalArgumentException("Matrix must be square");
    }
}