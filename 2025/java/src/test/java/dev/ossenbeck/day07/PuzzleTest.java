package dev.ossenbeck.day07;

import dev.ossenbeck.InputReader;
import dev.ossenbeck.Solvable;
import dev.ossenbeck.SolvableTest;

import java.nio.file.Path;

class PuzzleTest extends SolvableTest<Integer, Long> {
    private static final Path INPUT = COMMON_PATH.resolve("day07", "input.txt");

    @Override
    protected Solvable<Integer, Long> getSutPartOne() {
        return new Puzzle(new InputReader(INPUT));
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        return 21;
    }

    @Override
    protected Solvable<Integer, Long> getSutPartTwo() {
        return new Puzzle(new InputReader(INPUT));
    }

    @Override
    protected Long getExpectedResultPartTwo() {
        return 40L;
    }
}