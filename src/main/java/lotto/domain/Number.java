package lotto.domain;

import lotto.messageConstants.MessageConstants;

import java.util.Objects;

public class Number implements Comparable<Number> {
    private static final int MIN = 0;
    private static final int MAX = 45;

    private final int number;

    private Number(int number) {
        if (number < MIN || number > MAX) {
            throw new IllegalArgumentException(MessageConstants.ERROR_BOUND);
        }

        this.number = number;
    }

    public static Number create(int number) {
        return new Number(number);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
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
    public int compareTo(Number differentNumber) {
        if (this.number < differentNumber.number) {
            return -1;
        }
        if (this.number > differentNumber.number) {
            return 0;
        }
        return 1;
    }
}
