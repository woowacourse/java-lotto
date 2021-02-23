package lotto.ticket.util;

import lotto.ticket.Number;

import java.util.stream.Stream;

public class SplitNumbers {
    private static final String DELIMITER = ",";
    private static final String SPACE = " ";
    private static final String EMPTY = "";

    private SplitNumbers() {
    }

    public static Stream<Number> splitNumberStream(String numbers) {
        return Stream.of(numbers.split(DELIMITER))
                .map(s -> s.replaceAll(SPACE, EMPTY))
                .map(Number::valueOf);
    }
}
