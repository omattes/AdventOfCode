package dev.ossenbeck.day01;

import dev.ossenbeck.InputReader;
import dev.ossenbeck.Solvable;

import java.util.List;

public class Puzzle implements Solvable<Integer, Integer> {
    private final List<Rotation> rotations;

    public Puzzle(InputReader inputReader) {
        this.rotations = inputReader.asStream()
                .map(Rotation::parse)
                .toList();
    }

    @Override
    public Integer solvePartOne() {
        var dial = new Dial(50, 100);
        return (int) rotations.stream()
                .map(dial::rotate)
                .filter(position -> position == 0)
                .count();
    }

    @Override
    public Integer solvePartTwo() {
        var dial = new Dial(50, 100);
        return rotations.stream()
                .mapToInt(rotation -> countZeros(dial, rotation))
                .sum();
    }

    private int countZeros(Dial dial, Rotation rotation) {
        var previousPosition = dial.position();
        dial.rotate(rotation);
        return rotation.direction() == RotationDirection.LEFT ?
                ((dial.size() - previousPosition) % dial.size() + rotation.distance()) / dial.size()
                : (previousPosition + rotation.distance()) / dial.size();
    }
}