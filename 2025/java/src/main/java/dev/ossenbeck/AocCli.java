package dev.ossenbeck;


import dev.ossenbeck.common.Util;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import picocli.CommandLine;

import java.util.concurrent.Callable;

import static picocli.CommandLine.Command;
import static picocli.CommandLine.Option;


@Command(name = "aocrun",
        mixinStandardHelpOptions = true,
        version = "aocrun 1.0.0",
        description = "Runs Advent of Code puzzles for specified days.")
public class AocCli implements Callable<Integer> {
    @Option(
            names = {"-d", "--days"},
            description = "Space-separated list of days to run (e.g. 1 5 24).",
            defaultValue = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25"
    )
    private String days;

    @Option(
            names = {"-b", "--benchmark"},
            description = "Enables benchmark mode to measure runtime."
    )
    private boolean benchmark;

    @Option(
            names = {"-e", "--error"},
            description = "Prints exceptions."
    )
    private boolean showError;
    private final PuzzleRunner puzzleRunner = new PuzzleRunner();

    @Override
    public Integer call() {
        for (var day : Util.parseNumbers(days)) {
            try {
                puzzleRunner.run(day);
                if (benchmark) {
                    runBenchmarks(day);
                }
            } catch (Exception e) {
                if (showError) {
                    System.out.println("There was an error loading the puzzle for day " + day);
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    private void runBenchmarks(int day) throws RunnerException {
        System.out.println("Running benchmark for Day " + day);
        var options = new OptionsBuilder()
                .include(BenchmarkRunner.class.getSimpleName())
                .param("day", String.valueOf(day))
                .forks(1)
                .build();

        new Runner(options).run();
    }

    static void main(String... args) {
        var exitCode = new CommandLine(new AocCli()).execute(args);
        System.exit(exitCode);
    }
}