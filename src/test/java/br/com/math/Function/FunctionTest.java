package br.com.math.function;

import br.com.math.function.polynomial.Polynomial;
import br.com.math.function.trigonometric.Cosine;
import br.com.math.function.trigonometric.Sine;

import java.util.List;

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

        Sine f = new Sine();
        Polynomial g = new Polynomial(List.of(1.0, 2.0, -1.0, 3.0));
        Differentiable h = f.compose(g);
//        Polynomial serie = function.taylorSerie(10, 0.0);
        IO.println(f);
        IO.println(g);
        IO.println(h.apply(2.0));
//        IO.println(derivative.apply(1.0));

    }
}
