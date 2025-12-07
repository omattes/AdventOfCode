package dev.ossenbeck.common;

import java.util.Arrays;
import java.util.stream.Stream;

public enum Direction {
    NORTH(0, -1),
    NORTH_EAST(1, -1),
    EAST(1, 0),
    SOUTH_EAST(1, 1),
    SOUTH(0, 1),
    SOUTH_WEST(-1, 1),
    WEST(-1, 0),
    NORTH_WEST(-1, -1);

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public static Direction of(int x, int y) {
        return Arrays.stream(values())
                .filter(direction -> direction.x == x && direction.y == y)
                .findAny()
                .orElseThrow();
    }

    public static Direction of(String arrow) {
        return switch (arrow) {
            case "^" -> NORTH;
            case ">" -> EAST;
            case "v" -> SOUTH;
            case "<" -> WEST;
            default -> throw new IllegalArgumentException("Unknown arrow " + arrow);
        };
    }

    public String arrow() {
        return switch (this) {
            case NORTH -> "^";
            case EAST -> ">";
            case SOUTH -> "v";
            case WEST -> "<";
            default -> "";
        };
    }

    public Direction opposite() {
        return switch (this) {
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
            case WEST -> EAST;
            case EAST -> WEST;
            case NORTH_EAST -> SOUTH_WEST;
            case SOUTH_EAST -> NORTH_WEST;
            case SOUTH_WEST -> NORTH_EAST;
            case NORTH_WEST -> SOUTH_EAST;
        };
    }

    public Direction turnLeft() {
        return switch (this) {
            case NORTH -> WEST;
            case SOUTH -> EAST;
            case WEST -> SOUTH;
            case EAST -> NORTH;
            case NORTH_EAST -> NORTH_WEST;
            case SOUTH_EAST -> NORTH_EAST;
            case SOUTH_WEST -> SOUTH_EAST;
            case NORTH_WEST -> SOUTH_WEST;
        };
    }

    public Direction turnRight() {
        return switch (this) {
            case NORTH -> EAST;
            case SOUTH -> WEST;
            case WEST -> NORTH;
            case EAST -> SOUTH;
            case NORTH_EAST -> SOUTH_EAST;
            case SOUTH_EAST -> SOUTH_WEST;
            case SOUTH_WEST -> NORTH_WEST;
            case NORTH_WEST -> NORTH_EAST;
        };
    }

    public static Stream<Direction> cardinalDirections() {
        return Stream.of(NORTH, EAST, SOUTH, WEST);
    }

    public static Stream<Direction> ordinalDirections() {
        return Stream.of(NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST);
    }

    public static Stream<Direction> all() {
        return Stream.of(values());
    }
}
