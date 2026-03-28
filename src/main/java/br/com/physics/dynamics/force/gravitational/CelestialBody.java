package br.com.physics.dynamics.force.gravitational;

import java.math.BigDecimal;

public enum CelestialBody {
    SUN(274.000),
    MERCURY(3.70000),
    VENUS(8.87000),
    EARTH(9.80665),
    MOON(1.62500),
    MARS(3.71000),
    JUPITER(24.7900),
    SATURN(10.4400),
    URANUS(8.87000),
    NEPTUNE(11.1500),
    PLUTO(0.62000);

    public final BigDecimal gravity;

    CelestialBody(double gravity) {
        this.gravity = BigDecimal.valueOf(gravity);
    }

    static CelestialBody fromString(String name) {
        for (CelestialBody body : CelestialBody.values()) {
            if (body.name().equalsIgnoreCase(name.trim())) {
                return body;
            }
        }
        throw new IllegalArgumentException("Invalid CelestialBody name: " + name);
    }
}
