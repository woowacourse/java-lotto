package lotto.domain;

import java.util.Objects;

public class LottoNumber {

    static final int MINIMUM_LOTTO_NUMBER = 1;
    static final int MAXIMUM_LOTTO_NUMBER = 45;

    private final int number;

    public LottoNumber(final int number) {
        validate(number);
        this.number = number;
    }

    private void validate(final int number) {
        if (number < MINIMUM_LOTTO_NUMBER || number > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1과 45 사이여야 합니다.");
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof LottoNumber that)) {
            return false;
        }
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }

    public int getNumber() {
        return number;
    }
}
