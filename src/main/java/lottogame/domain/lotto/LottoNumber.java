package lottogame.domain.lotto;

import lottogame.utils.InvalidLottoNumberRangeException;

import java.util.Objects;

public class LottoNumber {
    public static final int LOTTO_MIN = 1;
    public static final int LOTTO_MAX = 45;
    private final int number;

    public LottoNumber(int number) {
        validNumberRange(number);
        this.number = number;
    }

    private void validNumberRange(int number) {
        if (number < LOTTO_MIN || number > LOTTO_MAX) {
            throw new InvalidLottoNumberRangeException();
        }
    }

    public LottoNumber values() {
        return new LottoNumber(number);
    }

    public int getNumber() {
        return number;
    }

    public boolean equals(int number) {
        return this.number == number;
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
