package lotto.domain;

import java.util.Objects;

final public class LottoNumber {
    public static final int MIN = 1;
    public static final int MAX = 45;

    private static final String NUMBER_RANGE_ERROR = "로또 숫자는 " + MIN + " 이상 " + MAX + " 이하의 숫자만 가능합니다.";

    private final int value;

    public LottoNumber(int lottoNumber) {
        validateNumber(lottoNumber);
        this.value = lottoNumber;
    }

    private void validateNumber(int number) {
        if (number < MIN || number > MAX) {
            throw new IllegalArgumentException(NUMBER_RANGE_ERROR);
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) other;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "LottoNumber{" +
                "lottoNumber=" + value +
                '}';
    }
}
