package br.com.math.function;

import br.com.math.function.exponential.Exponential;
import br.com.math.function.exponential.Logarithmic;
import br.com.math.function.polynomial.Polynomial;
import br.com.math.function.power.Power;
import br.com.math.function.trigonometric.Cosine;
import br.com.math.function.trigonometric.Sine;
import br.com.math.function.vectorial.Vectorial;

import static br.com.math.function.Differentiable.*;

public class VectorFunctionTest {

    static void main() {
        Vectorial v = new Vectorial(new Exponential(), new Logarithmic(), new Cosine(), new Sine());
        IO.println(v);
    }
}
