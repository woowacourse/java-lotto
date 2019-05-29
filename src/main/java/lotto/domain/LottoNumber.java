package lotto.domain;

import lotto.domain.domainexception.InvalidLottoNumberException;

import java.util.Objects;

import static lotto.domain.domainconstants.DomainConstants.MINIMUM_LOTTO_NUMBER;
import static lotto.domain.domainconstants.DomainConstants.MAXIMUM_LOTTO_NUMBER;

public class LottoNumber implements Comparable<LottoNumber> {
    private final int number;

    public LottoNumber(int number) {
        validLottoNumber(number);
        this.number = number;
    }

    private void validLottoNumber(int number) {
        if (MINIMUM_LOTTO_NUMBER > number || number > MAXIMUM_LOTTO_NUMBER) {
            throw new InvalidLottoNumberException("로또 넘버는 " + MINIMUM_LOTTO_NUMBER + "~" + MAXIMUM_LOTTO_NUMBER + "사이의 값을 가집니다.");
        }
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.number, o.number);
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