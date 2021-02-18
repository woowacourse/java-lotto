package lotto.ticket;

import java.util.Objects;

public class Number {
    private final int number;

    public Number(String value) {
        this.number = validate(value);
    }

    private int validate(String value) {
        int number = TicketValidation.validateNumber(value);
        TicketValidation.validateNumberInRange(number);
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number number1 = (Number) o;
        return number == number1.number;
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
