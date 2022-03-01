package model.lottonumber.vo;

import java.util.List;
import java.util.Objects;

public class Number {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final String NUMBER_RANGE_ERROR_MESSAGE = "[ERROR] 로또 번호는 1~45까지의 숫자로 입력하세요.";

    private final int number;

    public Number(final int number) {
        this.number = checkValidNumber(number);
    }

    private int checkValidNumber(final int number) {
        if (isNotInLottoNumberRange(number)) {
            throw new IllegalArgumentException(NUMBER_RANGE_ERROR_MESSAGE);
        }
        return number;
    }

    private boolean isNotInLottoNumberRange(final int number) {
        return number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER;
    }

    public boolean hasSameNumber(final List<Number> winningNumbers) {
        return winningNumbers.stream()
                .anyMatch(winningNumber -> this.number == winningNumber.number);
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Number that = (Number) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
