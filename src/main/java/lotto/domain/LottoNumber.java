package lotto.domain;

import lotto.Exception.InvalidLottoNumberException;

import java.util.Objects;

public class LottoNumber {
    static final int MAX_LOTTO_NUMBER = 45;
    static final int MIN_LOTTO_NUMBER = 1;
    private final int number;

    public LottoNumber(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new InvalidLottoNumberException("0~45 사이의 로또숫자만 가능합니다.");
        }
        this.number = number;
    }

    @Override
    public LottoNumber clone(){
        return new LottoNumber(number);
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
