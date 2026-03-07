package br.com.math.matrix;

public class RowColumnTest {

    public static void main(String[] args) {

        Matrix A = Matrix.intRandom(2,2, 0,10);
        ColumnMatrix B = ColumnMatrix.copyOf(A);
        System.out.println(A + "\n");
        System.out.println(B + "\n");
//        ColumnMatrix result = B.add(A);
//        System.out.println(result + "\n");

//        Matrix produce = A.multiply(B);
//        System.out.println(produce + "\n");
    }

    public static class TestaMatrizQuadrada {

        public static void main(String[] args) {

            double[][] elementosA = new double[2][2];
            elementosA[0][0] = 0.0;
            elementosA[0][1] = 0.0;
            elementosA[1][0] = 0.0;
            elementosA[1][1] = 1.0;

            SquareMatrix A = new SquareMatrix(elementosA);
            System.out.println(A);

    //        SquareMatrix B = A.powerOf(2);
    //        System.out.println();
    //        System.out.println(B);
    //
    //        int np = A.nilpotencyIndex();
    //        System.out.println();
    //        System.out.println(np);

    //        System.out.println(A.isSquare());
    //        System.out.println(A.isTriangular());
    //        System.out.println(A.isLower());
    //        System.out.println(A.isUpper());
    //        System.out.println(A.isDiagonal());
    //        System.out.println(A.isSymmetric());
    //        double det = A.determinant();
    //        System.out.println();
    //        System.out.println(det);

    //        DiagonalMatrix id = Matrix.identity(3);
    //        System.out.println();
    //        System.out.println(id);
    //
    //        SquareMatrix B = A.inverse();
    //        System.out.println();
    //        System.out.println(B);

    //        SquareMatrix result1 = A.multiplyBySquare(B);
    //        System.out.println();
    //        Matrix result2 = B.multiplyBy(A);
    //        System.out.println();
    //        System.out.println(result2);

    //        double[][] elementosC = new double[3][3];
    //        for (int i = 0; i < elementosC.length; i++) {
    //            for (int j = 0; j < elementosC[0].length; j++) {
    //                elementosC[i][j] = 2.0 * i + j;
    //            }
    //        }
    //
    //        LowerMatrix C = new LowerMatrix(elementosC);
    //        System.out.println();
    //        System.out.println(C);
    //
    //        LowerMatrix result = A.multiplyByLower(C);
    //        System.out.println();
    //        System.out.println(result);

    //
    //        double trace = A.trace();
    //        System.out.println(trace);

    //        double[][] elementos3 = new double[3][3];
    //        for (int i = 0; i < elementos3.length; i++) {
    //            for (int j = 0; j < elementos3[0].length; j++) {
    //                elementos3[i][j] = i + j;
    //            }
    //        }
        }
    }
}
