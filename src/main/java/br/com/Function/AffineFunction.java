package br.com.Function;

import br.com.Vector.*;

import java.util.List;
import java.util.Objects;

public class AffineFunction extends PolynomialFunction {

    private final double intercept;
    private final double slope;
    private final Behavior behavior;

    public AffineFunction(double intercept, double slope) {
        super(List.of(intercept, slope));
        this.intercept = intercept;
        this.slope = slope;
        behavior = slope == 0.0 ? Behavior.CONSTANT : slope < 0.0 ? Behavior.INCREASING : Behavior.DECREASING;
    }

    public AffineFunction(Vector v, Vector u) {
        double slope = slopeComputation(v, u);
        double intercept = v.get(1) - slope * v.get(0);
        this(intercept, slope);
    }

    public double getIntercept() {
        return intercept;
    }

    public double getSlope() {
        return slope;
    }

    public Behavior getBehavior() {
        return behavior;
    }

    @Override
    public Double apply(Double x) {
        return slope * x + intercept;
    }

    public Double zero() {
        return -1.0 * intercept / slope;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AffineFunction other)) return false;
        return Double.compare(other.slope, slope) == 0 &&
                Double.compare(other.intercept, intercept) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(slope, intercept);
    }





    /** Private methods **/

    private void validateSlope(double value) {
        if (value == 0.0)
            throw new IllegalArgumentException("The angular coefficient cannot be zero");
    }

    private static double slopeComputation(Vector v, Vector u) {
        Vector subtraction = validateSlopeComputation(v, u);
        return subtraction.get(1) / subtraction.get(0);
    }

    private static Vector validateSlopeComputation(Vector v, Vector u) {
        if (v.isZero() && u.isZero()) {
            throw new IllegalArgumentException("Vectors are both zero");
        }
        if (v.equals(u)) {
            return v.subtract(Vector.zero(v.getSize()));
        }
        if (v.subtract(u).get(0) == 0) {
            throw new IllegalArgumentException("Vectors lie at the same vertical line");
        }
        if (v.subtract(u).get(1) == 0) {
            throw new IllegalArgumentException("Vectors lie at the same horizontal line");
        }
        return v.subtract(u);
    }
}
