package lotto.domain;

import java.util.Objects;

public class LottoNumber {
    public static final String NUMBER_TYPE_ERROR = "숫자만 입력할 수 있습니다";
    public static final String NUMBER_RANGE_ERROR = "숫자를 1 ~ 45 사이로 입력해주세요";
    private static final int MAXIMUM_NUMBER = 45;
    private static final int MINIMUM_NUMBER = 1;
    private final int number;

    public LottoNumber(final String input) {
        number = validateNumberType(input);
        validateNumberRange();
    }

    private int validateNumberType(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            throw new IllegalArgumentException(NUMBER_TYPE_ERROR);
        }
    }

    private void validateNumberRange() {
        if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
            throw new IllegalArgumentException(NUMBER_RANGE_ERROR);
        }
    }

    public boolean isContain(final Lotto winLotto) {
        return winLotto.isContainNum(number);
    }

    public int getNumber() {
        return this.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
