package br.com.math.Matrix;

public class UpperMatrix extends TriangularMatrix {

    public UpperMatrix(int size) {
        super(size);
    }

    public UpperMatrix(double[][] data) {
        super(upperData(data));
    }

    public UpperMatrix(Matrix matrix) {
        this(matrix.getData());
    }


    public static UpperMatrix zero(int size) {
        return new UpperMatrix(size);
    }

    public UpperMatrix addUpper(Matrix other) {
        return computeSum(other);
    }

    public UpperMatrix multiplyByUpper(Matrix other) {
        return computeMultiplication(other);
    }

    @Override
    public UpperMatrix inverse() {
        validateUpper(super.inverse());
        return new UpperMatrix(super.inverse());
    }

    @Override
    public boolean isUpper() {
        return true;
    }

    @Override
    public LowerMatrix transpose() {
        return new LowerMatrix(super.transpose());
    }


    private static double[][] upperData(double[][] data) {
        int size = data.length;
        double[][] upData = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                upData[i][j] = (j < i) ? 0.0 : data[i][j];
            }
        }
        return upData;
    }

    private UpperMatrix computeSum(Matrix other) {
        validateUpper(other);
        UpperMatrix upp = new UpperMatrix(other);
        if (isZero()) return upp;
        if (upp.isZero()) return this;
        double[][] result = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                result[i][j] = data[i][j] + upp.data[i][j];
            }
        }
        return new UpperMatrix(result);
    }

    private UpperMatrix computeMultiplication(Matrix other) {
        validateUpper(other);
        UpperMatrix upp = new UpperMatrix(other);
        double[][] result = new double[size][size];
        if (other.isZero() || isZero()) return zero(size);
        for (int i = 0; i < size; i++) {
            for (int j = i; j < upp.size; j++) {
                for (int k = i; k <= j; k++) {
                    result[i][j] += data[i][k] * upp.data[k][j];
                }
            }
        }
        return new UpperMatrix(result);
    }

    private static void validateUpper(Matrix matrix) {
        if (!matrix.isUpper()) throw new IllegalArgumentException("Matrix is not upper");
    }
}
