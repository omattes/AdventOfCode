package dev.ossenbeck.common;

public record Coordinate(int x, int y) {
    public Coordinate move(Direction direction) {
        return move(direction, 1);
    }

    public Coordinate move(Direction direction, int steps) {
        return new Coordinate(x + steps * direction.x(), y + steps * direction.y());
    }

    public Coordinate addDistanceTo(Coordinate other) {
        var dX = this.x() - other.x();
        var dY = this.y() - other.y();
        return new Coordinate(x + dX, y + dY);
    }

    public int manhattanDistance(Coordinate other) {
        return Math.abs(x - other.x) + Math.abs(y - other.y);
    }
}
