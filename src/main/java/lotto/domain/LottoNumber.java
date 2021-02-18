package lotto.domain;

import lotto.exception.LottoCustomException;
import lotto.utils.ValidateUtils;

public class LottoNumber {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private final int number;

    public LottoNumber(final int number) {
        if (isNumberNotIRange(number)) {
            throw new LottoCustomException("로또 번호는 1-45사이의 숫자이어야 합니다");
        }
        this.number = number;
    }

    public LottoNumber(final String number) {
        this(ValidateUtils.parseInt(number));
    }

    public Integer getNumber() {
        return number;
    }

    private boolean isNumberNotIRange(int number) {
        return number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER;
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
