package model;

import java.util.Objects;

public class LottoNumber {
    private static final String LOTTO_NUMBER_FORM_ERROR_MESSAGE = "중복되지 않는 1 이상 45 이하의 정수여야합니다.\n";
    private static final int LOTTO_NAX_SIZE = 45;
    private static final int LOTTO_MIN_SIZE = 1;
    private final int number;

    public LottoNumber(int number) {
        if (number < LOTTO_MIN_SIZE || number > LOTTO_NAX_SIZE) {
            throw new IllegalArgumentException(LOTTO_NUMBER_FORM_ERROR_MESSAGE);
        }
        this.number = number;
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
        return Objects.hashCode(number);
    }
}
