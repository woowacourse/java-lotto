package lotto.domain;

import java.util.HashMap;
import java.util.Objects;
import lotto.exception.NumberException;

public class Number implements Comparable<Number> {

    private static final HashMap<Integer, Number> NUMBER_CACHE = new HashMap<>();
    public static final String INVALID_NUMBER_MESSAGE = "범위를 벗어난 숫자입니다.";
    public static final int UPPER_LIMIT = 45;
    public static final int LOWER_LIMIT = 1;

    private final int number;

    static {
        for (int i = LOWER_LIMIT; i <= UPPER_LIMIT; i++) {
            NUMBER_CACHE.put(i, new Number(i));
        }
    }

    private Number(int candidateNumber) {
        checkValidRange(candidateNumber);
        this.number = candidateNumber;
    }

    public static Number from(int candidateNumber) {
        checkValidRange(candidateNumber);
        return NUMBER_CACHE.get(candidateNumber);
    }

    private static void checkValidRange(int candidateNumber) {
        if ((candidateNumber < LOWER_LIMIT) || (candidateNumber > UPPER_LIMIT)) {
            throw new NumberException(INVALID_NUMBER_MESSAGE);
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
        if ((candidateObject == null) || (getClass() != candidateObject.getClass())) {
            return false;
        }
        Number object = (Number) candidateObject;
        return this.number == object.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.number);
    }

    @Override
    public String toString() {
        return Integer.toString(this.number);
    }
}
