package br.com.Function;

import java.awt.geom.AffineTransform;
import java.util.List;

public class FunctionTest {

    public static void main(String[] args) {


        PolynomialFunction function = new PolynomialFunction(List.of(5.0, 2.0, 6.0, 1.0, 3.0));
        double y = function.apply(1.0);
        PolynomialFunction derivative = function.derivative();
        PolynomialFunction derivative2 = derivative.derivative(2);
        System.out.println("\n" + function.apply(1.0));
        System.out.println("\n" + derivative.apply(1.0));
        System.out.println("\n" + derivative2.apply(1.0));

        /*
        AffineFunction function = new AffineFunction(1, 3);
        PolynomialFunction derivative = function.derivative();

        System.out.println(function);
        System.out.println(derivative);
        System.out.println(function.getDegree());
         */
    }
}
