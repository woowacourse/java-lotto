package lotto.domain;

import lotto.domain.validator.Validator;
import lotto.utils.NumberUtils;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    private int number;

    public LottoNumber(String number) {
        this(NumberUtils.parseNumber(number));
    }

    public LottoNumber(int number) {
        Validator.validateNumberScope(number);
        this.number = number;
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

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public int compareTo(LottoNumber lottoNumber) {
        return Integer.compare(this.number, lottoNumber.number);
    }
}
