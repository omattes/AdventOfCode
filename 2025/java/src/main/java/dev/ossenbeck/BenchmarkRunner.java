package dev.ossenbeck;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

public class BenchmarkRunner {
    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    public void benchmarkPart1(BenchmarkState state, Blackhole blackhole) {
        blackhole.consume(state.solvable.solvePartOne());
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    public void benchmarkPart2(BenchmarkState state, Blackhole blackhole) {
        blackhole.consume(state.solvable.solvePartTwo());
    }
}