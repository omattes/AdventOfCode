package dev.ossenbeck.day09;

import dev.ossenbeck.InputReader;
import dev.ossenbeck.Solvable;
import dev.ossenbeck.common.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.ossenbeck.common.Util.mod;

public class Puzzle implements Solvable<Long, Long> {
    private final List<Coordinate> redTiles;

    public Puzzle(InputReader inputReader) {
        this.redTiles = inputReader.asStream()
                .map(Util::parseNumbers)
                .map(n -> new Coordinate(n.getFirst(), n.getLast()))
                .toList();
    }

    @Override
    public Long solvePartOne() {
        var maxArea = 0L;
        for (var i = 0; i < redTiles.size(); i++) {
            var tile1 = redTiles.get(i);
            for (var j = i + 1; j < redTiles.size(); j++) {
                maxArea = Math.max(maxArea, Rectangle.area(tile1, redTiles.get(j)));
            }
        }
        return maxArea;
    }

    @Override
    public Long solvePartTwo() {
        var xWalls = new HashMap<Long, Ranges>();
        var yWalls = new HashMap<Long, Ranges>();
        var xInside = new HashMap<Long, Ranges>();
        var yInside = new HashMap<Long, Ranges>();

        buildBoundaries(xWalls, yWalls, xInside, yInside);
        trimWalls(xWalls, yInside);
        trimWalls(yWalls, xInside);

        var maxArea = 0L;
        for (var i = 0; i < redTiles.size(); i++) {
            var tile1 = redTiles.get(i);
            for (var j = i + 1; j < redTiles.size(); j++) {
                var rect = new Rectangle(tile1, redTiles.get(j));
                var area = rect.area();
                if (area <= maxArea) {
                    continue;
                }
                if (isValidRectangle(rect, xWalls, yWalls)) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }

    private Direction getMovementDirection(Coordinate current, Coordinate next) {
        var dx = Integer.compare(next.x(), current.x());
        var dy = Integer.compare(next.y(), current.y());
        return Direction.of(dx, dy);
    }

    private boolean intersects(long key1, Range range1, long key2, Range range2) {
        return range2.contains(key1) && range1.contains(key2);
    }

    private void buildBoundaries(Map<Long, Ranges> xWalls, Map<Long, Ranges> yWalls,
                                 Map<Long, Ranges> xInside, Map<Long, Ranges> yInside) {
        for (var i = 0; i < redTiles.size(); i++) {
            var current = redTiles.get(i);
            var next = redTiles.get(mod(i + 1, redTiles.size()));
            var movementDirection = getMovementDirection(current, next);
            var outside = current.move(movementDirection.turnLeft());
            if (current.y() == next.y()) {
                var range = new Range(Math.min(current.x(), next.x()), Math.max(current.x(), next.x()));
                addToMap(xWalls, outside.y(), range);
                addToMap(xInside, current.y(), range);
            } else {
                var range = new Range(Math.min(current.y(), next.y()), Math.max(current.y(), next.y()));
                addToMap(yWalls, outside.x(), range);
                addToMap(yInside, current.x(), range);
            }
        }
    }

    private void trimWalls(Map<Long, Ranges> walls, Map<Long, Ranges> inside) {
        for (var wallsEntry : walls.entrySet()) {
            for (var wall : wallsEntry.getValue()) {
                if (inside.containsKey(wall.end()) && inside.get(wall.end()).contains(wallsEntry.getKey())) {
                    wall.setEnd(wall.end() - 1);
                }
                if (inside.containsKey(wall.start()) && inside.get(wall.start()).contains(wallsEntry.getKey())) {
                    wall.setStart(wall.start() + 1);
                }
            }
        }
    }

    private void addToMap(Map<Long, Ranges> map, long key, Range range) {
        map.computeIfAbsent(key, _ -> new Ranges()).add(range);
    }

    private boolean isValidRectangle(Rectangle rect, Map<Long, Ranges> xWalls, Map<Long, Ranges> yWalls) {
        return isClear(xWalls, rect.yRange(), rect.a().x(), rect.b().x())
                && isClear(yWalls, rect.xRange(), rect.a().y(), rect.b().y());
    }

    private boolean isClear(Map<Long, Ranges> walls, Range edge, long vertex1, long vertex2) {
        for (var entry : walls.entrySet()) {
            if (!edge.contains(entry.getKey())) {
                continue;
            }
            for (var wall : entry.getValue()) {
                if (intersects(vertex1, edge, entry.getKey(), wall) || intersects(vertex2, edge, entry.getKey(), wall)) {
                    return false;
                }
            }
        }
        return true;
    }
}