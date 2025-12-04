package dev.ossenbeck.day03;

import dev.ossenbeck.InputReader;
import dev.ossenbeck.Solvable;

import java.util.List;
import java.util.stream.IntStream;

public class Puzzle implements Solvable<Long, Long> {
    private final List<int[]> banks;

    public Puzzle(InputReader inputReader) {
        this.banks = inputReader.asStream()
                .map(Puzzle::parseBatteries)
                .toList();
    }

    private static int[] parseBatteries(String line) {
        return line.chars().map(c -> c - '0').toArray();
    }

    @Override
    public Long solvePartOne() {
        return calculateTotalOutputVoltage(2);
    }

    @Override
    public Long solvePartTwo() {
        return calculateTotalOutputVoltage(12);
    }

    private long calculateTotalOutputVoltage(int allowedActiveBatteries) {
        return banks.stream()
                .mapToLong(bank -> calculateBankOutputVoltage(bank, allowedActiveBatteries))
                .sum();
    }

    private long calculateBankOutputVoltage(int[] bank, int allowedActiveBatteries) {
        var voltage = 0L;
        var activeBatteries = allowedActiveBatteries;
        for (var i = 0; i < bank.length && --activeBatteries >= 0; i++) {
            i = findIndexOfMaxValue(bank, i, bank.length - activeBatteries);
            voltage = voltage * 10 + bank[i];
        }
        return voltage;
    }

    private int findIndexOfMaxValue(int[] arr, int start, int end) {
        return IntStream.range(start, end)
                .reduce(start, (max, current) -> arr[current] > arr[max] ? current : max);
    }
}