package dev.ossenbeck.day06;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public enum Operator {
    ADDITION,
    MULTIPLICATION;

    private static final Pattern SYMBOL_PATTERN = Pattern.compile("[+*]");

    public static Operator fromSymbol(String symbol) {
        return switch (symbol) {
            case "+" -> ADDITION;
            case "*" -> MULTIPLICATION;
            default -> throw new IllegalArgumentException("Unknown operator symbol: " + symbol);
        };
    }

    public static List<Operator> parse(String line) {
        return SYMBOL_PATTERN.matcher(line).results()
                .map(MatchResult::group)
                .map(Operator::fromSymbol)
                .toList();
    }

    public long startValue() {
        return switch (this) {
            case ADDITION -> 0L;
            case MULTIPLICATION -> 1L;
        };
    }

    public long apply(long left, long right) {
        return switch (this) {
            case ADDITION -> left + right;
            case MULTIPLICATION -> left * right;
        };
    }
}
