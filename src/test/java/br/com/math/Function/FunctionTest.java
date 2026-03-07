package br.com.math.function;

import java.util.List;

import static br.com.math.function.TrigonometricType.*;

public class FunctionTest {

    public static void main(String[] args) {

        /*
        PolynomialFunction function = new PolynomialFunction(List.of(5.0, 2.0, 6.0, 1.0, 3.0));
        double y = function.apply(1.0);
        PolynomialFunction derivative = function.derivative(2);
        PolynomialFunction integral = function.integral(2);
        System.out.println("\n" + function);
        System.out.println("\n" + derivative);
        System.out.println("\n" + integral);

        AffineFunction function = new AffineFunction(1, 3);
        PolynomialFunction derivative = function.derivative();

        System.out.println(function);
        System.out.println(derivative);
        System.out.println(function.getDegree());
        */

        /*
        QuadraticFunction function = new QuadraticFunction(-6.0,-1.0,1.0);

        System.out.println(function.vertexForm());

        PolynomialFunction derivative = function.derivative(2);
         */

        DifferentiableFunction function = new ExponentialFunction(10, 1.0).derivative();

        PolynomialFunction serie = function.taylorSerie(5, 0.999);
        IO.println(serie);
        IO.println(serie.apply(0.001));
        IO.println(function.apply(1.0));

    }
}
