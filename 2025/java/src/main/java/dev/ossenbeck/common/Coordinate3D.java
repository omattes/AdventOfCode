package dev.ossenbeck.common;

public record Coordinate3D(long x, long y, long z) {
    public double getDistanceTo(Coordinate3D other) {
        var dX = this.x() - other.x();
        var dY = this.y() - other.y();
        var dZ = this.z() - other.z();
        return Math.sqrt(dX * dX + dY * dY + dZ * dZ);
    }
}
