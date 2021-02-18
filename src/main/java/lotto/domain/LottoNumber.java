package lotto.domain;

import lotto.exception.LottoCustomException;
import lotto.utils.ValidateUtils;

public class LottoNumber {

    private final int number;

    public LottoNumber(final int number) {
        if(isNotProperRange(number)){
            throw new LottoCustomException("로또 번호는 1-45사이의 숫자이어야 합니다");
        }
        this.number = number;
    }

    LottoNumber(final String number) {
        this(ValidateUtils.parseInt(number));
    }

    private boolean isNotProperRange(int number) {
        return !(number >= 1 && number <= 45);
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
