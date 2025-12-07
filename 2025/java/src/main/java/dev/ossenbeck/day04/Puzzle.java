package dev.ossenbeck.day04;

import dev.ossenbeck.InputReader;
import dev.ossenbeck.Solvable;
import dev.ossenbeck.common.Coordinate;
import dev.ossenbeck.common.Direction;
import dev.ossenbeck.common.Grid;

public class Puzzle implements Solvable<Integer, Integer> {
    private static final char PAPER_ROLL = '@';
    private static final char EMPTY_SPACE = '.';
    private static final int MAX_ADJACENT_ROLLS_FOR_ACCESS = 3;
    private final Grid grid;

    public Puzzle(InputReader inputReader) {
        this.grid = new Grid(
                inputReader.asStream()
                        .map(String::toCharArray)
                        .toArray(char[][]::new)
        );
    }

    @Override
    public Integer solvePartOne() {
        return removePaperRolls(grid.copy());
    }

    @Override
    public Integer solvePartTwo() {
        var diagram = grid.copy();
        var total = 0;
        var removed = 0;
        do {
            removed = removePaperRolls(diagram);
            total += removed;
        } while (removed > 0);
        return total;
    }

    private int removePaperRolls(Grid diagram) {
        var paperRollsWhichCanBeMoved = diagram.traverse()
                .filter(c -> isAccessibleByForklift(c, diagram))
                .toList();
        paperRollsWhichCanBeMoved.forEach(c -> diagram.replaceCharAt(c, EMPTY_SPACE));
        return paperRollsWhichCanBeMoved.size();
    }

    private boolean isAccessibleByForklift(Coordinate position, Grid diagram) {
        if (diagram.charAt(position) != PAPER_ROLL) {
            return false;
        }
        var occupied = 0;
        for (var direction : Direction.values()) {
            var adjacent = position.move(direction);
            if (diagram.isInBounds(adjacent) && diagram.charAt(adjacent) == PAPER_ROLL) {
                occupied++;
            }
        }
        return occupied <= MAX_ADJACENT_ROLLS_FOR_ACCESS;
    }
}