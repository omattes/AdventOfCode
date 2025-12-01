package dev.ossenbeck.day01;

public enum RotationDirection {
    LEFT(-1),
    RIGHT(1);

    private final int multiplier;

    RotationDirection(int multiplier) {
        this.multiplier = multiplier;
    }

    public int multiplier() {
        return multiplier;
    }

    public static RotationDirection valueOf(char c) {
        return switch (c) {
            case 'L' -> LEFT;
            case 'R' -> RIGHT;
            default -> throw new IllegalArgumentException("Unknown rotation direction: " + c);
        };
    }
}
