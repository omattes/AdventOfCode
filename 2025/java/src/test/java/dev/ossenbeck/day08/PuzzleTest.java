package dev.ossenbeck.day08;

import dev.ossenbeck.InputReader;
import dev.ossenbeck.Solvable;
import dev.ossenbeck.SolvableTest;

import java.nio.file.Path;

class PuzzleTest extends SolvableTest<Long, Long> {
    private static final Path INPUT = COMMON_PATH.resolve("day08", "input.txt");

    @Override
    protected Solvable<Long, Long> getSutPartOne() {
        return new Puzzle(new InputReader(INPUT), 10);
    }

    @Override
    protected Long getExpectedResultPartOne() {
        return 40L;
    }

    @Override
    protected Solvable<Long, Long> getSutPartTwo() {
        return new Puzzle(new InputReader(INPUT), 10);
    }

    @Override
    protected Long getExpectedResultPartTwo() {
        return 25272L;
    }
}