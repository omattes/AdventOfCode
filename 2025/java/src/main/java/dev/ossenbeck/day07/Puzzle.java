package dev.ossenbeck.day07;

import dev.ossenbeck.InputReader;
import dev.ossenbeck.Solvable;
import dev.ossenbeck.common.Coordinate;
import dev.ossenbeck.common.Direction;
import dev.ossenbeck.common.Grid;

import java.util.HashMap;
import java.util.Map;

public class Puzzle implements Solvable<Integer, Long> {
    public static final char SPLITTER = '^';
    private final Grid grid;
    private final Coordinate start;
    private final Map<Coordinate, Long> cache = new HashMap<>();

    public Puzzle(InputReader inputReader) {
        this.grid = new Grid(
                inputReader.asStream()
                        .map(String::toCharArray)
                        .toArray(char[][]::new)
        );
        this.start = grid.find('S');
    }

    @Override
    public Integer solvePartOne() {
        countSplits(start);
        return cache.size();
    }

    @Override
    public Long solvePartTwo() {
        return countSplits(start);
    }

    private long countSplits(Coordinate position) {
        if (cache.containsKey(position)) {
            return cache.get(position);
        }
        if (!grid.isInBounds(position)) {
            return 1;
        }
        if (grid.charAt(position) != SPLITTER) {
            return countSplits(position.move(Direction.SOUTH));
        }
        var splits = countSplits(position.move(Direction.WEST)) + countSplits(position.move(Direction.EAST));
        cache.put(position, splits);
        return splits;
    }
}