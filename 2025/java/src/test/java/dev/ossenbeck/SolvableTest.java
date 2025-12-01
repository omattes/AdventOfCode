package dev.ossenbeck;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class SolvableTest<T, U> {
    protected static final Path COMMON_PATH = Paths.get("src", "test", "java", "dev", "ossenbeck");

    protected abstract Solvable<T, U> getSutPartOne();

    protected abstract T getExpectedResultPartOne();

    protected abstract Solvable<T, U> getSutPartTwo();

    protected abstract U getExpectedResultPartTwo();

    @Test
    protected void testPartOne() {
        assertThat(getSutPartOne().solvePartOne()).isEqualTo(getExpectedResultPartOne());
    }

    @Test
    protected void testPartTwo() {
        assertThat(getSutPartTwo().solvePartTwo()).isEqualTo(getExpectedResultPartTwo());
    }
}