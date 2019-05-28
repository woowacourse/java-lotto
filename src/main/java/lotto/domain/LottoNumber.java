package lotto.domain;

import java.util.Objects;

import static lotto.domain.LottoRule.*;

public class LottoNumber {
    private final int number;

    public LottoNumber(int number) {
        this.number = number;
        checkValidRange();
    }

    private void checkValidRange() {
        if (number < MIN_LOTTO_NUMBER.get() || number > MAX_LOTTO_NUMBER.get()) {
            throw new IllegalArgumentException("유효한 로또 번호가 아닙니다.");
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
