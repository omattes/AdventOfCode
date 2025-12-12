package dev.ossenbeck;

import dev.ossenbeck.common.Grid;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public record InputReader(Path input) {
    public List<String> asList() {
        try {
            return Files.readAllLines(input, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public String asString() {
        try {
            return Files.readString(input, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public Stream<String> asStream() {
        try {
            return Files.lines(input, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public Grid asGrid() {
        return new Grid(asStream().map(String::toCharArray).toArray(char[][]::new));
    }
}
