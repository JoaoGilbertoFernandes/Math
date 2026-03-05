package br.com.Matrix;

public class TestaMatriz {

    public static void main(String[] args) {
        /* double[][] elementos1 = new double[2][2];
        for (int i = 0; i < elementos1.length; i++) {
            for (int j = 0; j < elementos1[0].length; j++) {
                elementos1[i][j] = 2*i - j;
            }
        }

        Matrix matrix1 = new Matrix(elementos1);
        System.out.println(matrix1 + "\n");
         */

//        Matrix A = Matrix.intRandom(2,2, -8, -10 );
//        System.out.println(A + "\n");

//        Matrix B = Matrix.intRandom(2,2, 0, 10);
//        System.out.println(B + "\n");
//
//        Matrix C = A.add(B);
//        System.out.println(C + "\n");


//        System.out.println(matrix1.getIndex(8.0));

//        double[] column = matrix1.getColumn(0);
//        Arrays.stream(column).forEach(e -> System.out.println("[" + e + "]"));

//        double[] row = matrix1.getRow(0);
//        String list = Arrays.stream(row)
//                .mapToObj(Double::toString)
//                .collect(Collectors.joining("   ", "[","]"));
//        System.out.println(list);

//        Matrix transposta = matrix1.transpose();
//        System.out.println(transposta);
//
//        double[][] elementos2 = new double[3][4];
//        for (int i = 0; i < elementos2.length; i++) {
//            for (int j = 0; j < elementos2[0].length; j++) {
//                elementos2[i][j] = 2.0 * i + j;
//            }
//        }
//
//        Matrix matrix2 = new Matrix(elementos2);
//        Matrix soma = matrix1.add(matrix2);
//        System.out.println(matrix2);
//        System.out.println(soma);
//
//        Matrix produto1 = matrix1.multiplyBy(matrix2);
//        System.out.println(produto1);
//        Matrix produto2 = matrix2.multiplyBy(matrix1);
//        System.out.println(produto2);
//
//        Matrix nula = Matrix.zero(4,3);
//        System.out.println(nula);
//
//        Matrix m = matrix1.multiplyByScalar(2);
//        System.out.println(m);
//
//        Matrix isZero = matrix1.multiplyBy(nula);
//        System.out.println(isZero);

        Matrix A = new Matrix(new double[][]{{2.0, -1.0}, {4.0, 3.0}});
        System.out.println("\n" + A);

        Matrix B = new Matrix(new double[][]{{1}, {17}});

        System.out.println("\n" + A.inverse().multiply(B));
    }
}
