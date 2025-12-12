package dev.ossenbeck.day06;

import dev.ossenbeck.InputReader;
import dev.ossenbeck.Solvable;
import dev.ossenbeck.SolvableTest;

import java.nio.file.Path;

class PuzzleTest extends SolvableTest<Long, Long> {
    private static final Path INPUT = COMMON_PATH.resolve("day06", "input.txt");

    @Override
    protected Solvable<Long, Long> getSutPartOne() {
        return new Puzzle(new InputReader(INPUT));
    }

    @Override
    protected Long getExpectedResultPartOne() {
        return 4277556L;
    }

    @Override
    protected Solvable<Long, Long> getSutPartTwo() {
        return new Puzzle(new InputReader(INPUT));
    }

    @Override
    protected Long getExpectedResultPartTwo() {
        return 3263827L;
    }
}