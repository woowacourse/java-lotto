package lotto.domain;

import java.util.Objects;
import lotto.utils.StringValidator;

public class LottoNumber {

    public static final int MINIMUM_VALUE = 1;
    public static final int MAXIMUM_VALUE = 45;

    private final int number;

    public LottoNumber(int number) {
        if (number < MINIMUM_VALUE || MAXIMUM_VALUE < number) {
            throw new RuntimeException();
        }
        this.number = number;
    }

    public LottoNumber(String numberValue) {
        this(convertToLottoNumber(numberValue));
    }

    private static int convertToLottoNumber(String numberValue) {
        numberValue = numberValue.trim();
        StringValidator.validateIsDigit(numberValue);
        return Integer.parseInt(numberValue);
    }

    public int toInt() {
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
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
