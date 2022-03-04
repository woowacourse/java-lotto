package domain;

import java.util.Objects;

public class Number {

    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;
    private static final String ERROR_NUMBER_OUT_RANGE = "로또 번호는 1에서 45 사이의 값을 입력해줘야 합니다.";

    private final int number;

    public Number(int number) {
        checkNumberRange(number);
        this.number = number;
    }

    private static void checkNumberRange(final int number) {
        if (number < MINIMUM_NUMBER || MAXIMUM_NUMBER < number) {
            throw new IllegalArgumentException(ERROR_NUMBER_OUT_RANGE);
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number that = (Number) o;
        return getNumber() == that.getNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber());
    }

    @Override
    public String toString() {
        return "LottoNumber{" +
                "number=" + number +
                '}';
    }
}
