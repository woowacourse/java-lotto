package lotto.ticket;

import java.util.Objects;

public class Number implements Comparable<Number> {
    private final int number;

    public Number(String value) {
        this.number = validate(value);
    }

    private int validate(String value) {
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
