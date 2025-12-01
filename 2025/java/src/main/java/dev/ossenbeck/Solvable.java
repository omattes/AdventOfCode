package dev.ossenbeck;

public interface Solvable<T, U> {
    T solvePartOne();

    U solvePartTwo();

    default void printParts() {
        var start1 = System.currentTimeMillis();
        var result1 = solvePartOne();
        var end1 = System.currentTimeMillis();
        System.out.println("Part 1: " + result1 + " in " + (end1 - start1) + "ms");

        var start2 = System.currentTimeMillis();
        var result2 = solvePartTwo();
        var end2 = System.currentTimeMillis();
        System.out.println("Part 2: " + result2 + " in " + (end2 - start2) + "ms");
    }
}
