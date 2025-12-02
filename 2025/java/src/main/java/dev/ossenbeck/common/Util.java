package dev.ossenbeck.common;

import java.util.List;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Util {
    public static final Pattern NUMBER_PATTERN = Pattern.compile("(-?\\d+)");
    public static final Pattern COMMA_DELIMITER = Pattern.compile(",");

    private static <T> List<T> parseNumbers(String line, Function<String, T> parseFunction) {
        return NUMBER_PATTERN.matcher(line).results()
                .map(MatchResult::group)
                .map(parseFunction)
                .toList();
    }

    public static List<Integer> parseNumbers(String line) {
        return parseNumbers(line, Integer::parseInt);
    }

    public static Integer parseInteger(String line) {
        return parseNumbers(line, Integer::parseInt).getFirst();
    }

    public static Long parseLong(String line) {
        return parseNumbers(line, Long::parseLong).getFirst();
    }

    public static int mod(int value, int mod) {
        return (value % mod + mod) % mod;
    }

    public static long mod(long value, long mod) {
        return (value % mod + mod) % mod;
    }
}