package br.com.Matrix;

import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Matrix {

    @Getter
    protected final int rows;
    @Getter
    protected final int cols;
    protected final double[][] data;

    public Matrix(int rows, int cols) {
        validateDimensions(rows, cols);
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows][cols];
    }

    public Matrix(double @NonNull [][] data) {
        this.rows = data.length;
        this.cols = data[0].length;
        this.data = matrixData(data);
    }

    public Matrix(@NonNull Matrix matrix) {
        this(matrix.getData());
    }


    /** Returns the identity {@code Matrix} of a specified size {@code size}.
     *
     * @param size the rows and columns number of the generated identity {@code Matrix}
     * @throws IllegalArgumentException if {@code size <= 0}.
     */
    public static @NonNull DiagonalMatrix identity(int size) {
        return computeIdentity(size);
    }

    /**
     * Returns a {@code rows} x {@code cols} {@code Matrix} of random integer elements
     * generated within a given range. The range extends from min({@code minRange}, {@code maxRange})
     * to max({@code minRange}, {@code maxRange}). If {@code minRange = maxRange},
     * the {@code minRange} value is assigned to all matrix's elements.
     *
     * @param rows the rows number of the generated {@code Matrix}
     * @param cols the columns number in the generated {@code Matrix}
     * @param minRange the floor integer of the range
     * @param maxRange the ceiling integer of the range
     *
     * @throws IllegalArgumentException if {@code rows <= 0} or {@code cols <= 0}
     */
    public static @NonNull Matrix intRandom(int rows, int cols, int minRange, int maxRange) {
        return computeIntRandom(rows, cols, minRange, maxRange);
    }

    /**
     * Returns a {@code rows} x {@code cols} {@code Matrix} of random elements generated within a given range.
     * The range extends from min({@code minRange}, {@code maxRange}) to max({@code minRange}, {@code maxRange}).
     * If {@code minRange = maxRange}, the {@code minRange} value is assigned to all matrix's elements.
     *
     * @param rows the rows number of the generated {@code Matrix}
     * @param cols the columns number of the generated {@code Matrix}
     * @param minRange the floor value of the range
     * @param maxRange the ceiling value of the range
     *
     * @throws IllegalArgumentException if {@code rows <= 0} or {@code cols <= 0}
     */
    public static @NonNull Matrix random(int rows, int cols, double minRange, double maxRange) {
        return computeRandom(rows, cols, minRange, maxRange);
    }

    /**
     * Returns a {@code rows} x {@code cols} {@code Matrix} of zero value elements.
     *
     * @param rows the number of rows of the generated {@code Matrix}
     * @param cols the number of columns of the generated {@code Matrix}
     *
     * @throws IllegalArgumentException if {@code rows <= 0} or {@code cols <= 0}
     */
    public static @NonNull Matrix zero(int rows, int cols) {
        validateDimensions(rows, cols);
        return new Matrix(rows, cols);
    }

    /**
     * Returns the determinant's value of this {@code Matrix} instance if it is equivalent
     * to a {@code SquareMatrix} type one ({@code this.rows == this.cols}).
     *
     * @throws IllegalStateException if {@code this.rows != this.cols}
     */
    public double det() {
        return computeDeterminant();
    }

    /**
     * Returns the inverse {@code Matrix} of this {@code Matrix} instance.
     *
     * @throws IllegalStateException if this determinant is zero
     */
    public @NonNull SquareMatrix inverse() {
        validateInverse();
        return cofactorMatrix().transpose().multiplyByScalar(Math.pow(det(), -1));
    }

    /**
     * Returns the trace of this {@code Matrix} instance.
     *
     * @throws IllegalStateException if {@code this.rows != this.cols}.
     */
    public double trace() {
        return computeTrace();
    }

    /**
     * Returns a {@code SquareMatrix} instance that is the product of this {@code Matrix} instance
     * with itself {@code n} times.
     *
     * @param n the power operation exponent
     *
     * @throws IllegalStateException if {@code this.rows != this.cols}
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public @NonNull SquareMatrix powerOf(int n) {
        return computePower(n);
    }

    /**
     * Returns the transpose of this {@code Matrix} instance.
     */
    public Matrix transpose() {
        return computeTranspose();
    }

    /**
     * Returns a {@code Matrix} instance that is the sum of this {@code Matrix} instance
     * with another given one.
     *
     * @param matrix the {@code Matrix} instance to be added
     *
     * @throws IllegalArgumentException if {@code matrix.rows != this.rows} or
     * {@code matrix.cols != this.cols}
     */
    public @NonNull Matrix add(@NonNull Matrix matrix) {
        return computeSum(matrix);
    }

    /**
     * Returns a {@code Matrix} instance that is the subtraction of this {@code Matrix} instance
     * with another given one.
     *
     * @param matrix the {@code Matrix} instance to be subtracted
     *
     * @throws IllegalArgumentException if {@code matrix.rows != this.rows} or
     * {@code matrix.cols != this.cols}
     */
    public @NonNull Matrix subtract(@NonNull Matrix matrix) {
        return add(matrix.multiplyByScalar(-1));
    }

    /**
     * Returns a {@code Matrix} instance that is the product of this {@code Matrix} instance
     * with a given number.
     *
     * @param value the number to be multiplied
     */
    public @NonNull Matrix multiplyByScalar(double value) {
        return computeScalarMultiplication(value);
    }

    /**
     * Returns a {@code Matrix} instance that is the product of this {@code Matrix} instance
     * with another given one. The {@code Matrix} multiplication is only valid when
     * {@code this.cols = matrix.rows}
     *
     * @param matrix the instance to be multiplied
     *
     * @throws IllegalArgumentException if {@code this.cols != matrix.rows}
     */
    public @NonNull Matrix multiply(@NonNull Matrix matrix) {
        return computeMultiplication(matrix);
    }

    /**
     * Returns a {@code Matrix} instance from this {@code Matrix} instance by eliminating
     * a given row and a given column. If {@code row < 0} ({@code col < 0}) it's value is set
     * to zero. If {@code row >= this.rows} ({@code col >= this.cols}) it's value is set to
     * {@code this.rows - 1} ({@code this.cols - 1}).
     *
     * @param row the index of the row to be deleted
     * @param col the index of the column to be deleted
     */
    public @NonNull Matrix subMatrix(int row, int col) {
        return computeSubMatrix(row, col);
    }

    /**
     * Returns the respect {@code row-col} cofactor of this {@code Matrix} instance.
     * If {@code row < 0} ({@code col < 0}) it's value is set to zero.
     * If {@code row >= this.rows} ({@code col >= this.cols}) it's value is set to
     * {@code this.rows - 1} ({@code this.cols - 1}).
     *
     * @param row the index of the row to be used to calculate the cofactor
     * @param col the index of the column to be used to calculate the cofactor
     *
     * @throws IllegalStateException if {@code this.rows != this.cols}
     */
    public double cofactor(int row, int col) {
        return Math.pow(-1, row + col) * subMatrix(row, col).computeDeterminant();
    }

    /**
     * Returns a {@code SquareMatrix} instance in which each element is it's
     * respective {@code cofactor(row, col)}.
     *
     * @throws IllegalStateException if {@code this.rows != this.cols}
     */
    public @NonNull SquareMatrix cofactorMatrix() {
        return computeCofactorMatrix();
    }

    /**
     * Returns {@code true} if all this {@code Matrix} elements are zero.
     */
    public boolean isZero() {
        return equals(zero(rows, cols));
    }

    /**
     * Returns {@code true} if each element{@code (i,j)} of this {@code Matrix} is equal to the respective
     * element{@code (j,i)}.
     */
    public boolean isSymmetric() {
        return equals(transpose());
    }

    /**
     * Returns true if this {@code Matrix} has the same number of rows and columns.
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean isSquare() {
        return rows == cols;
    }

    /**
     * Returns {@code true} if this {@code Matrix} has zero value elements in all positions
     * above or below the main diagonal.
     */
    public boolean isTriangular() {
        return (isLower() || isUpper());
    }

    /**
     * Returns {@code true} if this {@code Matrix} has zero value elements in all positions
     * below the main diagonal.
     */
    public boolean isUpper() {
        return equals(new UpperMatrix(this));
    }

    /**
     * Returns {@code true} if this {@code Matrix} has zero value elements in all positions
     * above the main diagonal.
     */
    public boolean isLower() {
        return equals(new LowerMatrix(this));
    }

    /**
     * Returns {@code true} if this {@code Matrix} has zero value elements in all positions
     * above and below the main diagonal.
     */
    public boolean isDiagonal() {
        return (isSymmetric() && isUpper());
    }

    /**
     * Returns {@code true} if this {@code Matrix} has just one column.
     */
    public boolean isColumn() {
        return cols == 1;
    }

    /**
     * Returns {@code true} if this {@code Matrix} has just one row.
     */
    public boolean isRow() {
        return rows == 1;
    }

    /**
     * Returns the element of index({@code row, col}). If {@code row < 0} ({@code col < 0})
     * it's value is set to zero. If {@code row >= this.rows} ({@code col >= this.cols})
     * it's value is set to {@code this.rows - 1} ({@code this.cols - 1}).
     *
     * @param row the row index of the element
     * @param col the column index of the element
     */
    public double get(int row, int col) {
        row = validateRow(row);
        col = validateColumn(col);
        return data[row][col];
    }

    /**
     * Returns a list that represents a specific row of this {@code Matrix}.
     * If {@code row < 0} it's value is set to zero. If {@code row >= this.rows - 1}
     * it's value is set to {@code this.rows - 1}.
     *
     * @param row the row index of this {@code Matrix}
     */
    public double @NonNull [] getRow(int row) {
        row = validateRow(row);
        return Arrays.copyOf(data[row], cols);
    }

    /**
     * Returns a list that represents a specific column of this {@code Matrix}.
     * If {@code col < 0} it's value is set to zero. If {@code col >= this.cols - 1}
     * it's value is set to {@code this.cols - 1}.
     *
     * @param col the column index of this {@code Matrix}
     */
    public double @NonNull [] getColumn(int col) {
        int valCol = validateColumn(col);
        return IntStream
                .range(0, rows)
                .mapToDouble(i -> data[i][valCol])
                .toArray();
    }

    /**
     * Returns all the elements of this {@code Matrix}.
     */
    public double @NonNull [][] getData() {
        return matrixData(data);
    }

    /**
     * Searches for the one or more {@code Index} related to the input value
     * and returns a list with the index (indices) found.
     *
     * @param element the value of the element(s) by which the index (indices)
     * is (are) found
     */
    public List<Index> getIndex(double element) {
        List<Index> indexList = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (Math.abs(data[i][j] - element) < 1e-9) {
                   indexList.add(new Index(i, j));
                }
            }
        }
        return indexList;
    }

    public Matrix addRow(double[] rowData, int row) {
        return computeAddRow(rowData, row);
    }

    public Matrix addRow(@NonNull Matrix matrix, int srcPos, int destPos) {
        return addRow(matrix.getRow(srcPos), destPos);
    }

    public Matrix removeRow(int row) {
        return computeRemoveRow(row);
    }

    public Matrix addColumn(double[] colData, int col) {
        return computeAddCol(colData, col);
    }

    public Matrix addColumn(@NonNull Matrix matrix, int srcCol, int destCol) {
        return addColumn(matrix.getColumn(srcCol), destCol);
    }

    public Matrix removeColumn(int col) {
        return computeRemoveCol(col);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("M(").append(rows).append("x").append(cols).append(")\n");
        for (int i = 0; i < rows; i++) {
            if (i != 0 && i != rows - 1) sb.append("| ");
            else sb.append("[ ");
            for (int j = 0; j < cols; j++) {
                double val = normalize(data[i][j]);
                sb.append(String.format("%2.0f", val));
                if (j < cols - 1) sb.append("  ");
            }
            if (i != 0 && i != rows - 1) sb.append(" |\n");
            else sb.append(" ]\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matrix m)) return false;
        if (rows != m.rows || cols != m.cols) return false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (Math.abs(data[i][j] - m.data[i][j]) > 1e-9) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(rows);
        result = 31 * result + Integer.hashCode(cols);
        result = 31 * result + Arrays.deepHashCode(data);
        return result;
    }



    /** PRIVATE METHODS */

    private static double @NonNull [][] matrixData(double @NonNull [][] data) {
        validateData(data);
        return Arrays.stream(data)
                .map(row -> Arrays.copyOf(row, row.length))
                .toArray(double[][]::new);
    }

    private static @NonNull DiagonalMatrix computeIdentity(int size) {
        validateDimensions(size, size);
        double[][] idData = new double[size][size];
        for (int i = 0; i < size; i++) {
            idData[i][i] = 1.0;
        }
        return new DiagonalMatrix(idData);
    }

    private @NonNull Matrix computeTranspose() {
        double[][] tpData = new double[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                tpData[i][j] = data[j][i];
            }
        }
        return new Matrix(tpData);
    }

    private double computeTrace() {
        validateTypeSquare();
        double sum = 0.0;
        for (int i = 0; i < rows; i++) {
            sum += data[i][i];
        }
        return sum;
    }

    private @NonNull Matrix computeSum(@NonNull Matrix other) {
        validateSum(other);
        if (isZero()) return other;
        if (other.isZero()) return this;
        double[][] result = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = data[i][j] + other.data[i][j];
            }
        }
        return new Matrix(result);
    }

    private @NonNull Matrix computeScalarMultiplication(double value) {
        double[][] result = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = data[i][j] * value;
            }
        }
        return new Matrix(result);
    }

    private @NonNull Matrix computeMultiplication(@NonNull Matrix other) {
        validateProduct(other);
        double[][] result = new double[rows][other.cols];
        if (isZero() || other.isZero()) return zero(rows, other.cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                for (int k = 0; k < cols; k++) {
                    result[i][j] += data[i][k] * other.data[k][j];
                }
            }
        }
        return new Matrix(result);
    }

    private @NonNull SquareMatrix computePower(int exponent) {
        validateTypeSquare();
        validateExponent(exponent);
        SquareMatrix sm = new SquareMatrix(this);
        SquareMatrix id = Matrix.identity(rows);
        if (sm.equals(id)) return id;

        Matrix result = new Matrix(id);
        for (int i = 0; i < exponent; i++) {
            result = result.multiply(this) ;
        }
        return new SquareMatrix(result);
    }

    private @NonNull Matrix computeSubMatrix(int row, int col) {
        row = validateRow(row);
        col = validateColumn(col);
        double[][] result = new double[rows - 1][cols - 1];
        int r = -1;
        for (int i = 0; i < rows; i++) {
            if (i == row) continue;
            r++;
            int c = -1;
            for (int j = 0; j < cols; j++) {
                if (j == col) continue;
                c++;
                result[r][c] = data[i][j];
            }
        }
        return new Matrix(result);
    }

    private @NonNull SquareMatrix computeCofactorMatrix() {
        double[][] result = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = cofactor(i, j);
            }
        }
        return new SquareMatrix(result);
    }

    private double computeDeterminant() {
        validateTypeSquare();
        int size = rows;
        double det = 0.0;
        for (int i = 0; i < size; i++) {
            boolean rowZero = true;
            boolean colZero = true;
            for (int j = 0; j < size; j++) {
                if (data[i][j] != 0) rowZero = false;
                if (data[j][i] != 0) colZero = false;
                if (!rowZero && !colZero) break;
            }
            if (rowZero || colZero) return 0.0;
        }
        if (size == 1) return data[0][0];
        if (size == 2) return (data[0][0] * data[1][1]) - (data[1][0] * data[0][1]);
        for (int j = 0; j < size; j++) {
            det += data[0][j] * cofactor(0, j);
        }
        return det;
    }

    private static @NonNull Matrix computeRandom(int rows, int cols, double min, double max) {
        validateDimensions(rows, cols);
        double[][] data = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = Math.random() * (max - min) + min;
            }
        }
        return new Matrix(data);
    }

    private static @NonNull Matrix computeIntRandom(int rows, int cols, int min, int max) {
        validateDimensions(rows, cols);
        double[][] data = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int value = max >= min ? 1 : -1;
                data[i][j] = (int) (Math.random() * (max - min + value) + min);
            }
        }
        return new Matrix(data);
    }

    private @NonNull Matrix computeAddRow(double[] data, int row) {
        row = validateAddRow(row);
        validateDataRow(data);
        double[][] newData = new double[rows + 1][cols];
        for (int i = 0; i <= row; i++) {
            if (i == row) {
                System.arraycopy(data, 0, newData[i], 0, cols);
            }
            else {
                System.arraycopy(this.data[i], 0, newData[i], 0, cols);
            }
        }
        for (int i = row; i < rows; i++) {
            System.arraycopy(this.data[i], 0, newData[i + 1], 0, cols);
        }
        return new Matrix(newData);
    }

    private @NonNull Matrix computeAddCol(double[] data, int col) {
        col = validateAddCol(col);
        validateDataCol(data);
        double[][] newData = new double[rows][cols + 1];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(this.data[i], 0, newData[i], 0, col);
            newData[i][col] = data[i];
            System.arraycopy(this.data[i], col, newData[i], col + 1, cols - col);
        }
        return new Matrix(newData);
    }

    private @NonNull Matrix computeRemoveRow(int row) {
        row = validateRow(row);
        double[][] newData = new double[rows - 1][cols];
        for (int i = 0, r = 0; i < rows; i++) {
            if (i == row) continue;
            System.arraycopy(this.data[i], 0, newData[r], 0, cols);
            r++;
        }
        return new Matrix(newData);
    }

    private @NonNull Matrix computeRemoveCol(int col) {
        col = validateColumn(col);
        double[][] newData = new double[rows][cols -1];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(data[i], 0, newData[i], 0, col);
            System.arraycopy(data[i], col + 1, newData[i], col, cols - col - 1);
        }
        return new Matrix(newData);
    }

    private static void validateDimensions(int rows, int cols) {
        if (rows <= 0 || cols <= 0) throw new IllegalArgumentException("Invalid dimensions");
    }

    private static void validateData(double @NonNull [][] data) {
        for (int i = 1; i < data.length; i++) {
            if (data[i].length != data[0].length) throw new IllegalArgumentException(
                    "Row " + i + " column count mismatch: expected " + data[0].length +
                            ", but found " + data[i].length
            );
        }
    }

    private void validateDataRow(double @NonNull [] data) {
        if (data.length != cols) throw new IllegalArgumentException();
    }

    private void validateDataCol(double @NonNull [] data) {
        if (data.length != rows) throw new IllegalArgumentException();
    }

    private void validateSum(@NonNull Matrix other) {
        if  (rows != other.rows || cols != other.cols) throw new IllegalArgumentException(
                "Matrix out of bounds: rows=" + other.rows + ", cols=" + other.cols +
                        ", size=" + rows + "x" + cols
        );
    }

    private void validateProduct(@NonNull Matrix other) {
        if (cols != other.rows) throw new IllegalArgumentException(
                "Matrix out of bounds: rows=" + other.rows + ", cols=" + other.cols +
                        ", size=" + rows + "x" + cols
        );
    }

    private void validateTypeSquare() {
        if(!isSquare()) throw new IllegalStateException("This operation is only defined for square matrix.");
    }

    private static void validateExponent(int exponent) {
        if (exponent < 0) throw new IllegalArgumentException("Exponent must be non-negative");
    }

    private void validateInverse() {
        if (det() == 0.0) throw new IllegalStateException("Matrix is not invertible");
    }

    private int validateRow(int row) {
        if (row < 0) return 0;
        if (row >= rows) return rows - 1;
        return row;
    }

    private int validateColumn(int col) {
        if (col < 0) return 0;
        if (col >= cols) return cols - 1;
        return col;
    }

    private int validateAddRow(int row) {
        if (row < 0) return 0;
        if (row >= rows) return rows;
        return row;
    }

    private int validateAddCol(int col) {
        if (col < 0) return 0;
        if (col >= cols) return cols;
        return col;
    }

    private static double normalize(double value) {
        return value == 0.0 ? 0.0 : value;
    }


    /*
     * protected double @NonNull [][] getData() {
     * double[][] cpData = new double[rows][cols];
     * for (int i = 0; i < rows; i++) {
     * System.arraycopy(data[i], 0, cpData[i], 0, cols);
     * }
     * return cpData;
     * }
     *
     * private static double @NonNull [][] matrixData(double @NonNull [][] data) {
     * validateData(data);
     * double[][] mtxData = new double[data.length][data[0].length];
     * for (int i = 0; i < data.length; i++) {
     * System.arraycopy(data[i], 0, mtxData[i], 0, data[i].length);
     * }
     * return mtxData;
     * }
     *
     * private @NonNull Matrix computeTranspose() {
     * double[][] tpData = IntStream
     * .range(0, cols)
     * .mapToObj(i -> IntStream
     * .range(0, rows)
     * .mapToDouble(j -> data[j][i])
     * .toArray())
     * .toArray(double[][]::new);
     * return new Matrix(tpData);
     * }
     * private @NonNull SquareMatrix computeIdentity(int size) {
     * double[][] idData = IntStream
     * .range(0, rows)
     * .mapToObj(i -> IntStream
     * .range(0, cols)
     * .mapToDouble(j -> (i == j ? 1.0 : 0.0))
     * .toArray()
     * ).toArray(double[][]::new);
     * return new SquareMatrix(idData);
     * }
     */

    /* private @NonNull SquareMatrix computeInverse() {
     * this.validateTypeSquare();
     * this.validateInverse();
     * int size = rows;
     * double[][] invData = Matrix.identity(size).getData();
     * double[][] data = this.getData();
     * for (int i = 0; i < size; i++) {
     * double pivot = data[i][i];
     * int l = i;
     * if (pivot == 0.0) {
     * for (int j = i + 1; j < size; j++) {
     * if (data[j][i] == 0.0) continue;
     * l = j;
     * break;
     * }
     * double[] row = data[l];
     * data[l] = data[i];
     * data[i] = row;
     *
     * double[] invRow = invData[l];
     * invData[l] = invData[i];
     * invData[i] = invRow;
     *
     * pivot = data[l][l];
     * }
     * for (int j = 0; j < size; j++) {
     * data[i][j] /= pivot;
     * invData[i][j] /= pivot;
     * }
     * for (int j = 0; j < size; j++) {
     * if (j != i) {
     * double factor = data[j][i];
     * for (int k = 0; k < size; k++) {
     * data[j][k] -= factor * data[i][k];
     * invData[j][k] -= factor * invData[i][k];
     * }
     * }
     * }
     * }
     * return new SquareMatrix(invData);
     * }
     */
}