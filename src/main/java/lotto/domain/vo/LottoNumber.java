package lotto.domain.vo;

import lotto.constants.LottoNumberLimit;

import java.util.Objects;

public class LottoNumber {

    private final int number;

    public LottoNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    private void validateRange(int number) {
        if (LottoNumberLimit.checkOutOfRange(number)) {
            throw new IllegalArgumentException("로또 번호는 1이상 45이하이어야 한다.");
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
        if (!(o instanceof LottoNumber)) {
            return false;
        }
        LottoNumber number = (LottoNumber) o;
        return this.number == number.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
