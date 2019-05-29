package lotto.domain.lotto;

import lotto.domain.InvalidLottoNumberException;

import java.util.*;

public class LottoNumber {

    private final int number;

    public LottoNumber(int number) {
        if(number < 1 || number > 45){
            throw new InvalidLottoNumberException("1부터 45까지의 수가 아닙니다.");
        }
        this.number = number;
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
