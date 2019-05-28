package lotto.domain;

import java.util.Objects;

import lotto.exceptions.InvalidLottoNumberException;

public class LottoNumber implements Comparable<LottoNumber> {
    private final int lottoNumber;

    private LottoNumber(int lottoNumber) {
        if (lottoNumber < 0) {
            throw new InvalidLottoNumberException("로또 번호의 범위는 1-45 입니다.");
        }
        this.lottoNumber = lottoNumber;
    }

    public String getLottoNumber() {
        return String.valueOf(lottoNumber);
    }

    public static LottoNumber of(int number) {
        return new LottoNumber(number);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.lottoNumber, o.lottoNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return lottoNumber == that.lottoNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }
}
