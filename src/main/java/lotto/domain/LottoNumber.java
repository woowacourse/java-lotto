
package lotto.domain;

import lotto.utils.ValidateUtils;

public class LottoNumber {

    private final int number;

    public LottoNumber(final int number) {
        this.number = number;
    }

    LottoNumber(final String number) {
        this(ValidateUtils.parseInt(number));
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber lottoNumber = (LottoNumber) o;
        return number == lottoNumber.number;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(number);
    }

}
