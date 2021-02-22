package lotto.domain;

import java.util.Objects;
import lotto.utils.CustomException;
import lotto.utils.StringChecker;

public class LottoNumber {
    public static final int MINIMUM_NUMBER = 1;
    public static final int MAXIMUM_NUMBER = 45;

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
        if (!StringChecker.isNumber(numberValue)) {
            throw new CustomException("로또 넘버는 숫자이어야 합니다.");
        }
    }

    private void validateNumberRange(int number) {
        if (number < MINIMUM_NUMBER || MAXIMUM_NUMBER < number) {
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
