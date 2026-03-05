package br.com.physics;

import br.com.math.Function.PolynomialFunction;


public class Motion1D {

    private final PolynomialFunction equationOfMotion;
    private double position;
    private double speed;
    private double acceleration;

    public Motion1D(PolynomialFunction equationOfMotion) {
        this.equationOfMotion = equationOfMotion;
        position = equationOfMotion.get(0);
        speed = equationOfMotion.derivative().get(0);
        acceleration = equationOfMotion.derivative(2).get(0);
    }

    public double getPosition(double time) {
        return equationOfMotion.apply(time);
    }

    public double getSpeed(double time) {
        return equationOfMotion.derivative().apply(time);
    }

    public double getAcceleration(double time) {
        return equationOfMotion.derivative(2).apply(time);
    }

    public double avarageSpeed(double inicialTime, double finalTime) {
        return (equationOfMotion.apply(finalTime) - equationOfMotion.apply(inicialTime)) /
                (finalTime - equationOfMotion.apply(inicialTime));
    }
}
