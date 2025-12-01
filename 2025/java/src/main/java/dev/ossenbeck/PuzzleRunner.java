package dev.ossenbeck;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PuzzleRunner {
    private final String moduleName;
    private final Path input;

    public PuzzleRunner() {
        this.moduleName = PuzzleRunner.class.getPackageName();
        this.input = Paths.get("src", "main", "java", moduleName.replace(".", File.separator));
    }

    public void run(int day) throws Exception {
        var formattedDay = String.format("%02d", day);
        var fullQualifiedClassName = moduleName + ".day" + formattedDay + ".Puzzle";
        var inputFile = input.resolve("day" + formattedDay, "input.txt");
        var puzzleInstance = Class.forName(fullQualifiedClassName)
                .getDeclaredConstructor(InputReader.class)
                .newInstance(new InputReader(inputFile));
        if (puzzleInstance instanceof Solvable<?, ?> solvable) {
            System.out.println("Day " + day);
            solvable.printParts();
            System.out.println();
        }
    }
}