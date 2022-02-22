package lotto.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class LottoNumber {
    private static final Pattern LOTTO_NUMBER_REGEX = Pattern.compile("^[0-9]");
    private static final String INVALID_LOTTO_NUMBER_EXCEPTION_MESSAGE = "로또 번호는 1 ~ 45 사이의 자연수여야합니다.";

    private final int number;

    public LottoNumber(final String value) {
        validateNotNegativeInteger(value);
        int number = Integer.parseInt(value);
        validateRange(number);
        this.number = number;
    }

    private void validateNotNegativeInteger(final String value) {
        if (!LOTTO_NUMBER_REGEX.matcher(value).matches()) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    private void validateRange(final int value) {
        if (value < 1 || value > 45) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_EXCEPTION_MESSAGE);
        }
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
