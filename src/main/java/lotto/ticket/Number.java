package lotto.ticket;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Number implements Comparable<Number> {


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
        TicketValidation.validateNumber(value);
        int number = Integer.parseInt(value);
        TicketValidation.validateNumberInRange(number);
        return number;
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
