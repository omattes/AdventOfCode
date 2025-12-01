package dev.ossenbeck.day01;

import static dev.ossenbeck.common.Util.mod;

public class Dial {
    private final int size;
    private int position;

    public Dial(int start, int size) {
        this.size = size;
        this.position = start;
    }

    public int position() {
        return position;
    }

    public int size() {
        return size;
    }

    public int rotate(Rotation rotation) {
        position = mod(position + rotation.direction().multiplier() * rotation.distance(), size);
        return position;
    }
}