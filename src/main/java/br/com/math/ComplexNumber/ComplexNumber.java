package br.com.math.complexNumber;

import br.com.math.vector.Vector;
import br.com.math.vector.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class ComplexNumber {

    private final double real;

    private final double imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = normalizeZero(real);
        this.imaginary = normalizeZero(imaginary);
    }

    public ComplexNumber(Vector vector) {
        real = vector.dotProduct(new Vector2D(1,0));
        imaginary = vector.dotProduct(new Vector2D(0,1));
    }


    public ComplexNumber conjugate() {
        return new ComplexNumber(real, -1.0 * imaginary);
    }

    public double norm() {
        return Math.sqrt(real * real + imaginary * imaginary);
    }

    public ComplexNumber add(ComplexNumber other) {
        return new ComplexNumber(real + other.real, imaginary + other.imaginary);
    }

    public ComplexNumber subtract(ComplexNumber other) {
        return new ComplexNumber(real - other.real, imaginary - other.imaginary);
    }

    public double getImaginary() {
        return imaginary;
    }

    public double getReal() {
        return real;
    }

    public ComplexNumber multiply(ComplexNumber other) {
        double real = this.real * other.real - this.imaginary * other.imaginary;
        double imaginary = this.real * other.imaginary + this.imaginary * other.real;
        return new ComplexNumber(real, imaginary);
    }

    public ComplexNumber divide(ComplexNumber other) {
        return multiply(other.conjugate()).multiply(new ComplexNumber(Math.pow(other.norm(), -2), 0));
    }

    public ComplexNumber power(double exponent) {
        double theta = Math.atan2(imaginary, real);
        return new ComplexNumber(Math.cos(theta * exponent), Math.sin(theta * exponent));
    }

    public List<ComplexNumber> roots(int n) {
        if (equals(new ComplexNumber(0,0))) {
            return List.of(this);
        }
        List<ComplexNumber> rootList = new ArrayList<>();
        double theta = Math.atan2(imaginary, real) / n;
        double rootNorm = Math.pow(norm(), 1.0 / n);
        for (int i = 0; i < n; i++) {
            double thetaRoot = (theta + 2 * i * Math.PI) / n;
            rootList.add(new ComplexNumber(
                    rootNorm * Math.cos(thetaRoot),
                    rootNorm * Math.sin(thetaRoot)
                    )
            );
        }
        return rootList;
    }

    @Override
    public String toString() {
        if (imaginary == 0.0) {
            return String.format("%.2f", real);
        }
        if (real == 0.0) {
            if (Math.abs(imaginary) == 1.0) {
                return imaginary > 0 ? "i" : "-i";
            } else {
                return String.format("%.2fi", imaginary);
            }
        }
        String sign = imaginary > 0.0 ? "+" : "-";
        if (Math.abs(imaginary) == 1.0) {
            return String.format("%.2f %s i ", real, sign);
        } else {
            return String.format("%.2f %s %.2fi", real, sign, Math.abs(imaginary));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComplexNumber cn)) return false;
        return Double.compare(real, cn.real) == 0
                && Double.compare(imaginary, cn.imaginary) == 0;
    }

    @Override
    public int hashCode() {
        int result = Double.hashCode(real);
        result = 31 * result + Double.hashCode(imaginary);
        return result;
    }



    private static double normalizeZero(double value) {
        if (Math.abs(value) < 1e-10) {
            return 0.0;
        }
        return value;
    }

}
