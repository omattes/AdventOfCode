package dev.ossenbeck.day02;

import dev.ossenbeck.InputReader;
import dev.ossenbeck.Solvable;

import java.util.List;

import static dev.ossenbeck.common.Util.COMMA_DELIMITER;

public class Puzzle implements Solvable<Long, Long> {
    private final List<ProductIdRange> productIdRanges;

    public Puzzle(InputReader inputReader) {
        this.productIdRanges = COMMA_DELIMITER.splitAsStream(inputReader.asString())
                .map(ProductIdRange::parse)
                .toList();
    }

    @Override
    public Long solvePartOne() {
        return productIdRanges.stream()
                .flatMapToLong(ProductIdRange::range)
                .filter(productId -> hasEqualHalves(String.valueOf(productId)))
                .sum();
    }

    @Override
    public Long solvePartTwo() {
        return productIdRanges.stream()
                .flatMapToLong(ProductIdRange::range)
                .filter(productId -> hasRepeatingSlices(String.valueOf(productId)))
                .sum();
    }

    private boolean hasEqualHalves(String productId) {
        return hasRepeatingSlices(productId, 2);
    }

    private boolean hasRepeatingSlices(String productId) {
        return hasRepeatingSlices(productId, productId.length());
    }

    private boolean hasRepeatingSlices(String productId, int maxSlices) {
        for (var slices = 2; slices <= maxSlices; slices++) {
            if (isSliceRepeating(productId, slices)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSliceRepeating(String productId, int slices) {
        if (productId.length() % slices != 0) {
            return false;
        }
        var sliceSize = productId.length() / slices;
        for (var i = sliceSize; i < productId.length(); i++) {
            if (productId.charAt(i) != productId.charAt(i % sliceSize)) {
                return false;
            }
        }
        return true;
    }
}