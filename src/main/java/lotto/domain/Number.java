package lotto.domain;

public class Number implements Comparable<Number> {
    private static final String ERROR_MESSAGE = "숫자의 범위는 1-45입니다.";
    private static final int MIN = 1;
    private static final int MAX = 45;

    private final int number;

    public Number(int number) {
        validOverBound(number);
        this.number = number;
    }

    private void validOverBound(int input) {
        if (input < MIN || input > MAX) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
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

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
