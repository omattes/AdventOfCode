package dev.ossenbeck.day05;

import dev.ossenbeck.InputReader;
import dev.ossenbeck.Solvable;
import dev.ossenbeck.common.Range;
import dev.ossenbeck.common.Ranges;

import java.util.List;

import static dev.ossenbeck.common.Util.DOUBLE_LINE_SEPARATOR;
import static dev.ossenbeck.common.Util.parseLongs;

public class Puzzle implements Solvable<Long, Long> {
    private final Ranges freshIngredientIds;
    private final List<Long> ingredientIds;

    public Puzzle(InputReader inputReader) {
        var data = DOUBLE_LINE_SEPARATOR.split(inputReader.asString());
        this.freshIngredientIds = parseFreshIngredientRanges(data[0]);
        this.ingredientIds = parseLongs(data[1]);
    }

    private Ranges parseFreshIngredientRanges(String input) {
        var freshIngredientRanges = new Ranges();
        var freshIngredientIds = parseLongs(input);
        for (var i = 1; i < freshIngredientIds.size(); i += 2) {
            var start = freshIngredientIds.get(i - 1);
            var end = freshIngredientIds.get(i);
            freshIngredientRanges.add(new Range(start, -1L * end));
        }
        return freshIngredientRanges;
    }

    @Override
    public Long solvePartOne() {
        return ingredientIds.stream()
                .filter(freshIngredientIds::contains)
                .count();
    }

    @Override
    public Long solvePartTwo() {
        return freshIngredientIds.count();
    }
}