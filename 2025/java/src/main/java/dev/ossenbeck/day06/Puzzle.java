package dev.ossenbeck.day06;

import dev.ossenbeck.InputReader;
import dev.ossenbeck.Solvable;
import dev.ossenbeck.common.Grid;
import dev.ossenbeck.common.Util;

import java.util.List;

public class Puzzle implements Solvable<Long, Long> {
    private final List<Operator> operators;
    private final Grid inputWorksheet;

    public Puzzle(InputReader inputReader) {
        var lines = inputReader.asList();
        this.operators = Operator.parse(lines.removeLast());
        this.inputWorksheet = Grid.from(lines);
    }

    @Override
    public Long solvePartOne() {
        var worksheet = parseRowsToLongs();
        var columnSize = worksheet.getFirst().size();
        var sum = 0L;
        for (var column = 0; column < columnSize; column++) {
            var operator = operators.get(column);
            var result = operator.startValue();
            for (var row : worksheet) {
                result = operator.apply(result, row.get(column));
            }
            sum += result;
        }
        return sum;
    }

    private List<List<Long>> parseRowsToLongs() {
        return inputWorksheet.stream()
                .map(String::new)
                .map(Util::parseLongs)
                .toList();
    }

    @Override
    public Long solvePartTwo() {
        var worksheet = inputWorksheet.transpose();
        var sum = 0L;
        var problemIndex = 0;
        var operator = operators.get(problemIndex++);
        var result = operator.startValue();
        for (var row = 0; row < worksheet.height(); row++) {
            var operand = calculateOperand(worksheet, row);
            if (operand == 0L) {
                sum += result;
                operator = operators.get(problemIndex++);
                result = operator.startValue();
            } else {
                result = operator.apply(result, operand);
            }
        }
        return sum + result;
    }

    private long calculateOperand(Grid worksheet, int row) {
        var operand = 0L;
        for (var column = 0; column < worksheet.width(); column++) {
            if (Character.isDigit(worksheet.charAt(column, row))) {
                operand = operand * 10 + worksheet.charAt(column, row) - '0';
            }
        }
        return operand;
    }
}