package dev.ossenbeck;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@State(Scope.Thread)
public class BenchmarkState {
    private final String moduleName;
    private final Path input;
    @Param("1")
    public int day;

    Solvable<?, ?> solvable;

    public BenchmarkState() {
        this.moduleName = BenchmarkState.class.getPackageName();
        this.input = Paths.get("src", "main", "java", moduleName.replace(".", File.separator));
    }

    @Setup
    public void createPuzzle() throws Exception {
        var formattedDay = String.format("%02d", day);
        var fullQualifiedClassName = moduleName + ".day" + formattedDay + ".Puzzle";
        var inputFile = input.resolve("day" + formattedDay, "input.txt");
        var puzzleInstance = Class.forName(fullQualifiedClassName)
                .getDeclaredConstructor(InputReader.class)
                .newInstance(new InputReader(inputFile));
        if (puzzleInstance instanceof Solvable<?, ?> s) {
            this.solvable = s;
        }
    }
}