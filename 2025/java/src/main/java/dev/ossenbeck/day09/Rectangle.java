package dev.ossenbeck.day09;

import dev.ossenbeck.common.Coordinate;
import dev.ossenbeck.common.Range;

public record Rectangle(Coordinate a, Coordinate b, Range xRange, Range yRange) {
    public Rectangle(Coordinate a, Coordinate b) {
        this(a, b,
                new Range(Math.min(a.x(), b.x()), Math.max(a.x(), b.x())),
                new Range(Math.min(a.y(), b.y()), Math.max(a.y(), b.y()))
        );
    }

    public long area() {
        return area(a, b);
    }

    public static long area(Coordinate a, Coordinate b) {
        var dx = Math.abs(a.x() - b.x()) + 1;
        var dy = Math.abs(a.y() - b.y()) + 1;
        return (long) dx * dy;
    }
}