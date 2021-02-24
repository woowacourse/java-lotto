package lotto.domain;

import java.util.Objects;
import lotto.exception.NumberException;

public class Number implements Comparable<Number> {

    public static final String EXCESS_NUMBER_MESSAGE = "범위를 벗어난 숫자입니다.";
    public static final int UPPER_LIMIT = 45;
    public static final int LOWER_LIMIT = 1;

    private final int number;

    public Number(int candidateNumber) {
        checkValidRange(candidateNumber);
        this.number = candidateNumber;
    }

    private void checkValidRange(int candidateNumber) {
        if ((candidateNumber < LOWER_LIMIT) | (candidateNumber > UPPER_LIMIT)) {
            throw new NumberException(EXCESS_NUMBER_MESSAGE);
        }
    }

    @Override
    public int compareTo(Number target) {
        return Integer.compare(this.number, target.number);
    }

    @Override
    public boolean equals(Object candidateObject) {
        if (this == candidateObject) {
            return true;
        };
        if (candidateObject == null || getClass() != candidateObject.getClass()) {
            return false;
        }
        Number object = (Number) candidateObject;
        return number == object.number;
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
