package dev.ossenbeck.day08;

import dev.ossenbeck.common.Coordinate3D;

public record JunctionBoxConnection(Coordinate3D box1, Coordinate3D box2, double distance)
        implements Comparable<JunctionBoxConnection> {
    @Override
    public int compareTo(JunctionBoxConnection o) {
        return Double.compare(this.distance, o.distance);
    }
}