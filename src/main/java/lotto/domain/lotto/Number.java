package lotto.domain.lotto;

import java.util.Objects;

import lotto.domain.exceptions.LottoNumberException;

public class Number implements Comparable<Number> {
    private static final String INVALID_LOTTO_NUMBER = "로또 번호의 범위는 1-45 입니다.";
    private static final int NUMBER_LOWER_BOUND = 1;
    private static final int NUMBER_UPPER_BOUND = 45;

    private final int number;

    public Number(int number) {
        valid(number);
        this.number = number;
    }

    public static Number of(int number) {
        return new Number(number);
    }

    private void valid(int number) {
        if (!isRange(number)) {
            throw new LottoNumberException(INVALID_LOTTO_NUMBER);
        }
    }

    private boolean isRange(int number) {
        return NUMBER_LOWER_BOUND <= number && number <= NUMBER_UPPER_BOUND;
    }

    public String getNumber() {
        return String.valueOf(number);
    }

    @Override
    public int compareTo(Number o) {
        return Integer.compare(this.number, o.number);
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
        return String.valueOf(number);
    }
}
