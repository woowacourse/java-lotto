package lotto.domain;

import lotto.exception.LottoCustomException;

import static java.lang.String.format;

public class LottoNumber {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private final int number;

    public LottoNumber(final int number) {
        if (isNumberNotInRange(number)) {
            throw new LottoCustomException(format(
                    "로또 번호는 %d-%d사이의 숫자이어야 합니다",
                    MIN_LOTTO_NUMBER,
                    MAX_LOTTO_NUMBER));
        }
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    private boolean isNumberNotInRange(int number) {
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
