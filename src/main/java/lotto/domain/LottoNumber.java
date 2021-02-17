package lotto.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class LottoNumber {
    private static final String IS_NUMBER = "\\d+";

    private final int number;

    public LottoNumber(int number) {
        if (number < 1 || 45 < number) {
            throw new RuntimeException();
        }
        this.number = number;
    }

    public LottoNumber(String numberValue) {
        this(convertToLottoNumber(numberValue));
    }

    private static int convertToLottoNumber(String numberValue) {
        numberValue = numberValue.trim();
        if (!Pattern.matches(IS_NUMBER, numberValue)) {
            throw new RuntimeException();
        }
        return Integer.parseInt(numberValue);
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
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

}
