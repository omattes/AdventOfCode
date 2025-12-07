package dev.ossenbeck.common;

public class Range {
    private long start;
    private long end;

    public Range(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public long start() {
        return start;
    }

    public long end() {
        return end;
    }

    public long length() {
        return end - start + 1;
    }

    public boolean contains(Range o) {
        return start <= o.start && end >= o.end;
    }

    public boolean contains(long value) {
        return start <= value && end >= value;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Range[%s - %s]".formatted(start, end);
    }
}