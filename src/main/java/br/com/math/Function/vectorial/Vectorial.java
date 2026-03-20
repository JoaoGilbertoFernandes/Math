package br.com.math.function.vectorial;

import br.com.math.function.Differentiable;
import br.com.math.function.Integrable;
import br.com.math.function.polynomial.Polynomial;
import br.com.math.function.power.Power;
import br.com.math.vector.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static br.com.math.function.Differentiable.*;

public class Vectorial {

    private final int size;

    private final List<Differentiable> functions;

    public Vectorial(Differentiable f, Differentiable g) {
        size = 2;
        functions = List.of(f, g);
    }

    public Vectorial(Differentiable f, Differentiable g, Differentiable h) {
        size = 3;
        functions = List.of(f, g, h);
    }

    public Vectorial(List<? extends Differentiable> functions) {
        size = functions.size();
        this.functions = List.copyOf(functions);
    }


    public static Vectorial zero(int size) {
        List<Polynomial> zeroFunctions = IntStream.range(0, size)
                .mapToObj(i -> zeroFunction()).toList();

        return new Vectorial(zeroFunctions);
    }

    public Vectorial add(Vectorial other) {
        validateSize(other.size);
        List<Differentiable> result = IntStream.range(0, size)
                .mapToObj(i -> get(i).add(other.get(i)))
                .toList();

        return new Vectorial(result);
    }

    public Vectorial subtract(Vectorial other) {
        return add(other.multiply(-1));
    }

    public Vectorial multiply(double value) {
        List<Differentiable> function = functions.stream()
                .map(f -> f.multiply(value))
                .toList();

        return new Vectorial(function);
    }

    public Vector coordinates(double t) {
        double[] data = functions.stream()
                .mapToDouble(f -> f.apply(t))
                .toArray();

        return new Vector(data);
    }

    public Differentiable dotProduct(Vectorial other) {
        validateSize(other.size);
        return IntStream.range(0, size)
                .mapToObj(i -> get(i).multiply(other.get(i)))
                .reduce(zeroFunction(), Differentiable::add);
    }

    public Vectorial crossProduct(Vectorial other) {
        validateSize(3);
        validateSize(other);

        Differentiable f = get(1).multiply(other.get(2))
                .subtract(get(2).multiply(other.get(1)));

        Differentiable g = get(2).multiply(other.get(0))
                .subtract(get(0).multiply(other.get(2)));

        Differentiable h = get(0).multiply(other.get(1))
                .subtract(get(1).multiply(other.get(0)));

        return new Vectorial(f, g, h);
    }

    public Vectorial derivative() {
        List<Differentiable> result = functions.stream()
                .map(Differentiable::derivative)
                .toList();

        return new Vectorial(result);
    }

    public Vectorial derivative(int order) {
        List<Differentiable> result = functions.stream()
                .map(f -> f.derivative(order))
                .toList();

        return new Vectorial(result);
    }

    public Vectorial integral() {
        List<Differentiable> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Integrable f = (Integrable) functions.get(i);
            Integrable g = f.integral();
            result.add(g);
        }
        return new Vectorial(result);
    }

    public Vectorial integral(int order) {
        List<Integrable> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Integrable f = (Integrable) functions.get(i);
            Integrable g = f.integral(order);
            result.add(g);
        }
        return new Vectorial(result);
    }

    public Differentiable norm() {
        Power sqr = new Power(1.0);
        return sqr.compose(dotProduct(this));
    }

    public Vectorial normalized() {
        validateNormalization();
        List<Differentiable> result = functions.stream()
                .map(f -> f.divide(norm()))
                .toList();

        return new Vectorial(result);
    }

    public Differentiable get(int index) {
        return functions.get(index);
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < size; i++) {
            Differentiable val = Objects.equals(get(i), zeroFunction()) ? zeroFunction() : get(i);
            sb.append(val);
            if (i < size - 1) sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }




    private void validateSize(Vectorial vectorial) {
        if (vectorial.size != size) throw new IllegalArgumentException();
    }

    private void validateSize(int size) {
        if (this.size != size) throw new IllegalArgumentException();
    }

    private void validateNormalization() {
        if (norm().isZeroFunction()) throw new ArithmeticException("Cannot normalize zero vector");
    }
}
