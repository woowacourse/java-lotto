package lotto.domain.lotto;

import lotto.domain.InvalidLottoNumberException;

import java.util.*;

import static lotto.domain.lotto.Lotto.*;

public class LottoNumber {

    private final int number;

    public LottoNumber(int number) {
        if(number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER){
            throw new InvalidLottoNumberException("1부터 45까지의 수가 아닙니다.");
        }
        this.number = number;
    }

    public int getNumber() {
        return number;
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

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
