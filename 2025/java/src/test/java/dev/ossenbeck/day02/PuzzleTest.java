package dev.ossenbeck.day02;

import dev.ossenbeck.InputReader;
import dev.ossenbeck.Solvable;
import dev.ossenbeck.SolvableTest;

import java.nio.file.Path;

class PuzzleTest extends SolvableTest<Long, Long> {
    private static final Path INPUT = COMMON_PATH.resolve("day02", "input.txt");

    @Override
    protected Solvable<Long, Long> getSutPartOne() {
        return new Puzzle(new InputReader(INPUT));
    }

    @Override
    protected Long getExpectedResultPartOne() {
        return 1227775554L;
    }

    @Override
    protected Solvable<Long, Long> getSutPartTwo() {
        return new Puzzle(new InputReader(INPUT));
    }

    @Override
    protected Long getExpectedResultPartTwo() {
        return 4174379265L;
    }
}