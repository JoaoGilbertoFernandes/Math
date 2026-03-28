package br.com.physics.dynamics.force.gravitational;

import br.com.math.function.Differentiable;
import br.com.math.function.vectorial.Vectorial;
import br.com.math.vector.Vector;
import br.com.physics.dynamics.energy.ConservativeForce;

import java.math.BigDecimal;
import java.util.stream.IntStream;

import static br.com.physics.dynamics.force.gravitational.CelestialBody.*;


public class Gravitational implements ConservativeForce {

    private final BigDecimal mass;
    private final Vectorial field;

    public Gravitational(double mass) {
        this(mass, EARTH);
    }

    public Gravitational(double mass, String celestialBody) {
        this(mass, fromString(celestialBody));
    }

    public Gravitational(double mass, CelestialBody celestialBody) {
        this(mass, celestialBody.gravity.doubleValue());
    }

    public Gravitational(double mass, double gravity) {
        this(mass, new Vectorial(Differentiable.constantFunction(gravity)));
    }

    public Gravitational(double mass, Vectorial field) {
        this.mass = new BigDecimal(mass);
        this.field = field;
    }

    public BigDecimal getMass() {
        return mass;
    }
    
    public Vector getField(double ... position) {
        return getField(new Vector(position));
    }
    
    public Vector getField(Vector position) {
        return getField().getVector(position);
    }

    public Vectorial getField() {
        return field;
    }

    public Vector getValue(double ... position) {
        return getValue(new Vector(position));
    }

    public Vector getValue(Vector position) {
        return getValue().getVector(position);
    }

    public Vectorial getValue() {
        return field.multiply(mass.doubleValue());
    }

    @Override
    public Differentiable potential() {
        Vectorial integral = field.integral();

        return IntStream.range(0, field.size())
                .mapToObj(integral::get)
                .reduce(Differentiable.zeroFunction(), Differentiable::add);
    }
}
