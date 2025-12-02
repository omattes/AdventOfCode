package dev.ossenbeck.day02;

import java.util.stream.LongStream;

public record ProductIdRange(long start, long end) {
    public static ProductIdRange parse(String line) {
        var data = line.split("-");
        return new ProductIdRange(Long.parseLong(data[0]), Long.parseLong(data[1]));
    }

    public LongStream range() {
        return LongStream.rangeClosed(start, end);
    }
}
