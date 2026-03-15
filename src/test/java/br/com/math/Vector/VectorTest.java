package br.com.math.vector;

public class VectorTest {

    public static void main(String[] args) {

        /*
         * Vector v = new Vector(new double[]{1, 2, 3});
         * System.out.println(v + "\n");
         *
         * Vector e1 = new Vector(new double[]{1, 0, 0});
         * System.out.println(e1 + "\n");
         *
         * Vector e2 = new Vector(new double[]{0, 1, 0});
         * System.out.println(e2 + "\n");
         *
         * Vector e3 = new Vector(new double[]{0, 0, 1});
         * System.out.println(e3 + "\n");

         * System.out.println(v.canonicalBasis() + "\n");
         */

        /* Vector v = new Vector(1,2);
         * System.out.println("\n" + v);
         * System.out.println("\n" + v.toBasis());
         * Vector u = new Vector(3,2);
         * Vector w = new Vector(-2,3);
         * List<Vector> basis = new ArrayList <>();
         * basis.add(u);
         * basis.add(w);
         * System.out.println("\n" + v.projection(u));
         * System.out.printf("\n" + "[%.2f" + u + " , " + "%.2f" + w + "]"
         * , v.baseChange(basis).get(0), v.baseChange(basis).get(1));
         */

        /*
        * Vector x = new Vector(1, 0, 0);
        * Vector y = new Vector(0, 1, 0);
        * Vector z = new Vector(0, 0, 1);
        * Vector v = new Vector(1, 4, 2);
        * Vector w = new Vector(3, 2, 3);
        */

        /*
        * System.out.println("\n" + x.rejection(y));
        * System.out.println("\n" + Math.toDegrees(v.toSpherical().theta()));
        * System.out.println("\n" + Math.toDegrees(v.toSpherical().phi()));
        */

        Vector v = new Vector(3,4,1);
        System.out.println("\n" + v.toCylindrical());
        System.out.println("\n" + v.toSpherical());
        System.out.println("\n" + v.toBasis());
    }
}
