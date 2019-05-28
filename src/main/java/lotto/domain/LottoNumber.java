package lotto.domain;

import lotto.domain.exception.InvalidLottoNumberException;

import java.util.Objects;

public class LottoNumber {
    private final int number;

    public LottoNumber(final int number) {
        if (number < 1 || number > 45) {
            throw new InvalidLottoNumberException(number + "는 유효한 범위가 아닙니다. 로또 번호는 1부터 45 사이여야합니다.");
        }
        this.number = number;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
