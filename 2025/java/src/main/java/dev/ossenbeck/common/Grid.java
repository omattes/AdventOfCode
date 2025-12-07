package dev.ossenbeck.common;

import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static dev.ossenbeck.common.Util.mod;

public record Grid(char[][] grid) {
    public boolean isInBounds(Coordinate coordinate) {
        return coordinate.y() >= 0 && coordinate.y() < height()
                && coordinate.x() >= 0 && coordinate.x() < width();
    }

    public boolean areInBounds(Coordinate coordinate, List<Direction> directions) {
        return directions.stream()
                .map(coordinate::move)
                .allMatch(this::isInBounds);
    }

    public Stream<Coordinate> traverse() {
        return IntStream.range(0, height())
                .boxed()
                .flatMap(y -> IntStream.range(0, width()).mapToObj(x -> new Coordinate(x, y)));
    }

    public char charAt(Coordinate coordinate) {
        return grid[coordinate.y()][coordinate.x()];
    }

    public void replaceCharAt(Coordinate coordinate, char newChar) {
        grid[coordinate.y()][coordinate.x()] = newChar;
    }

    public void print() {
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                System.out.print(grid[y][x]);
            }
            System.out.println();
        }
    }

    public void print(Set<Coordinate> toHighlight) {
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                if (toHighlight.contains(new Coordinate(x, y))) {
                    System.out.print('X');
                    continue;
                }
                System.out.print(grid[y][x]);
            }
            System.out.println();
        }
    }

    public Coordinate find(char charToFind) {
        return traverse()
                .filter(coordinate -> charAt(coordinate) == charToFind)
                .findAny()
                .orElseThrow();
    }

    public Stream<Coordinate> findAll(char charToFind) {
        return traverse().filter(coordinate -> charAt(coordinate) == charToFind);
    }

    public int width() {
        return grid[0].length;
    }

    public int height() {
        return grid.length;
    }

    public Coordinate wrapAround(Coordinate c) {
        return new Coordinate(mod(c.x(), width()), mod(c.y(), height()));
    }

    public Grid copy() {
        return new Grid(
                IntStream.range(0, height())
                        .mapToObj(y -> grid[y].clone())
                        .toArray(char[][]::new)
        );
    }
}