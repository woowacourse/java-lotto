package lotto.domain;

import java.util.Objects;
import java.util.regex.Pattern;
import lotto.utils.CustomException;

public class LottoNumber {
    private static final String IS_NUMBER = "\\d+";

    private final int number;


    public LottoNumber(String numberValue) {
        this(convertToLottoNumber(numberValue));
    }

    public LottoNumber(int number) {
        validateNumberRange(number);
        this.number = number;
    }

    private static int convertToLottoNumber(String numberValue) {
        numberValue = numberValue.trim();
        validateIsNumber(numberValue);
        return Integer.parseInt(numberValue);
    }

    private static void validateIsNumber(String numberValue) {
        if (!Pattern.matches(IS_NUMBER, numberValue)) {
            throw new CustomException("로또 넘버는 숫자이어야 합니다.");
        }
    }

    private void validateNumberRange(int number) {
        if (number < 1 || 45 < number) {
            throw new CustomException("로또 넘버는 1~45 사이 정수이어야 합니다.");
        }
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
