package domain;

import static validator.NumberValidators.validateLottoNumberRange;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    private final int number;

    private LottoNumber (int value) {
        number = value;
    }

    public static LottoNumber of(int value) {
        validateLottoNumberRange(value);
        return new LottoNumber(value);
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(LottoNumber lottoNumber) {
        return this.number - lottoNumber.getNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
