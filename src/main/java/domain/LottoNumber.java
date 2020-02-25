package domain;

import java.util.Objects;

public class LottoNumber implements  Comparable<LottoNumber>{
    private static final int MIN_LOTTO_NUMBER_RANGE = 1;
    private static final int MAX_LOTTO_NUMBER_RANGE = 45;
    private static final String MAKE_STR = "";

    private int lottoNumber;

    LottoNumber(int input) {
        validateLottoNumberRange(input);
        this.lottoNumber = input;
    }

    private static void validateLottoNumberRange(int number) {
        if(number < MIN_LOTTO_NUMBER_RANGE || number > MAX_LOTTO_NUMBER_RANGE) {
            throw new IllegalArgumentException("범위를 벗어나는 로또 숫자입니다.");
        }
    }

    @Override
    public int compareTo(LottoNumber number) {
        return this.lottoNumber - number.lottoNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return lottoNumber == that.lottoNumber;
    }

    @Override
    public String toString() {
        return lottoNumber + MAKE_STR;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }
}
