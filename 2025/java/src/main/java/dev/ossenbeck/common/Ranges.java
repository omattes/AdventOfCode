package dev.ossenbeck.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ranges implements Iterable<Range> {
    private final List<Range> values = new ArrayList<>();

    public Ranges add(Ranges ranges) {
        ranges.values.forEach(this::merge);
        return this;
    }

    public Ranges add(Range range) {
        merge(range);
        return this;
    }

    public Ranges add(long value) {
        merge(new Range(value, value));
        return this;
    }

    private void merge(Range range) {
        var i = findInsertIndex(range);
        values.add(i, range);
        mergeOverlappingOrTouchingRanges(i == 0 ? 0 : i - 1);
    }

    private int findInsertIndex(Range range) {
        var low = 0;
        var high = values.size() - 1;
        while (low <= high) {
            var mid = (high + low) / 2;
            if (values.get(mid).start() < range.start()) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private void mergeOverlappingOrTouchingRanges(int iStart) {
        for (var i = iStart; i < values.size() - 1; i++) {
            var current = values.get(i);
            var next = values.get(i + 1);
            if (current.end() + 1 >= next.start()) {
                current.setStart(Math.min(current.start(), next.start()));
                current.setEnd(Math.max(current.end(), next.end()));
                values.remove(i-- + 1);
            }
        }
    }

    public long size() {
        return values.size();
    }

    public boolean contains(long value) {
        for (var range : values) {
            if (range.contains(value)) {
                return true;
            }
        }
        return false;
    }

    public long count() {
        var count = 0L;
        for (var range : values) {
            count += range.length();
        }
        return count;
    }

    @Override
    public String toString() {
        return "Ranges{" +
                "values=" + values +
                '}';
    }
    
    @Override
    public Iterator<Range> iterator() {
        return values.iterator();
    }
}
