package dev.ossenbeck.day08;

import dev.ossenbeck.InputReader;
import dev.ossenbeck.Solvable;
import dev.ossenbeck.common.Coordinate3D;
import dev.ossenbeck.common.Util;

import java.util.*;

public class Puzzle implements Solvable<Long, Long> {
    private final List<Coordinate3D> junctionBoxPositions;
    private final int maxConnections;

    public Puzzle(InputReader inputReader) {
        this(inputReader, 1_000);
    }

    public Puzzle(InputReader inputReader, int maxConnections) {
        this.junctionBoxPositions = inputReader.asStream()
                .map(Util::parseNumbers)
                .map(n -> new Coordinate3D(n.get(0), n.get(1), n.get(2)))
                .toList();
        this.maxConnections = maxConnections;
    }

    @Override
    public Long solvePartOne() {
        var connections = new PriorityQueue<JunctionBoxConnection>(Comparator.reverseOrder());
        var circuits = new ArrayList<Set<Coordinate3D>>();
        calculateAllDistances(connections, true, circuits);

        while (!connections.isEmpty()) {
            var shortestConnection = connections.poll();
            var circuit1 = findCircuit(circuits, shortestConnection.box1());
            var circuit2 = findCircuit(circuits, shortestConnection.box2());
            if (!circuit1.equals(circuit2)) {
                circuit1.addAll(circuit2);
                circuits.remove(circuit2);
            }
        }

        return circuits.stream()
                .mapToLong(Set::size)
                .sorted()
                .skip(circuits.size() - 3)
                .reduce(1L, Math::multiplyExact);
    }

    @Override
    public Long solvePartTwo() {
        var connections = new PriorityQueue<JunctionBoxConnection>();
        var circuits = new ArrayList<Set<Coordinate3D>>();
        calculateAllDistances(connections, false, circuits);

        while (!connections.isEmpty()) {
            var shortestConnection = connections.poll();
            var circuit1 = findCircuit(circuits, shortestConnection.box1());
            var circuit2 = findCircuit(circuits, shortestConnection.box2());
            if (!circuit1.equals(circuit2)) {
                circuit1.addAll(circuit2);
                if (circuit1.size() == junctionBoxPositions.size()) {
                    return shortestConnection.box1().x() * shortestConnection.box2().x();
                }
                circuits.remove(circuit2);
            }
        }
        throw new IllegalStateException("Could not connect all junction boxes");
    }

    private void calculateAllDistances(Queue<JunctionBoxConnection> connections, boolean limitConnections, List<Set<Coordinate3D>> circuits) {
        for (var i = 0; i < junctionBoxPositions.size(); i++) {
            var box1 = junctionBoxPositions.get(i);
            circuits.add(new HashSet<>(Set.of(box1)));
            for (var j = i + 1; j < junctionBoxPositions.size(); j++) {
                var box2 = junctionBoxPositions.get(j);
                connections.add(new JunctionBoxConnection(box1, box2, box1.getDistanceTo(box2)));
                if (limitConnections && connections.size() > maxConnections) {
                    connections.poll();
                }
            }
        }
    }

    private Set<Coordinate3D> findCircuit(List<Set<Coordinate3D>> circuits, Coordinate3D junctionBoxPosition) {
        return circuits.stream()
                .filter(circuit -> circuit.contains(junctionBoxPosition))
                .findAny()
                .orElseThrow();
    }
}