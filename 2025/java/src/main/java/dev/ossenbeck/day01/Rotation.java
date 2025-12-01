package dev.ossenbeck.day01;

public record Rotation(RotationDirection direction, int distance) {
    public static Rotation parse(String line) {
        return new Rotation(
                RotationDirection.valueOf(line.charAt(0)),
                Integer.parseInt(line.substring(1))
        );
    }
}