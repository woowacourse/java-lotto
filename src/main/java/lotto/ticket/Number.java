package lotto.ticket;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Number implements Comparable<Number> {
    public static final String ERROR_MESSAGE_INVALID_INPUT = "잘못된 입력입니다.";
    public static final String ERROR_MESSAGE_INVALID_RANGE = "숫자는 " + Ticket.MIN_NUMBER + "부터 " + Ticket.MAX_NUMBER + "사이여야 합니다.";

    private static final List<Number> cache;

    static {
        cache = IntStream.rangeClosed(Ticket.MIN_NUMBER, Ticket.MAX_NUMBER)
                .mapToObj(Integer::toString)
                .map(Number::new)
                .collect(Collectors.toList());
    }

    private final int number;

    private Number(String value) {
        this.number = validate(value);
    }

    public static Number valueOf(String value) {
        int number = validate(value);
        return cache.get(number - 1);
    }

    public static List<Number> values() {
        return Collections.unmodifiableList(cache);
    }

    private static int validate(String value) {
        int number = validateNumber(value);
        validateNumberInRange(number);
        return number;
    }

    public static int validateNumber(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_INPUT);
        }
    }

    private static void validateNumberInRange(int number) {
        if (number < Ticket.MIN_NUMBER || number > Ticket.MAX_NUMBER) {
            throw new IllegalStateException(ERROR_MESSAGE_INVALID_RANGE);
        }
    }

    @Override
    public int compareTo(Number o) {
        return Integer.compare(this.number, o.number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number that = (Number) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
