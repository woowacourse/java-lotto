package lotto.domain;

import lotto.exception.LottoCustomException;

public class LottoNumber {

    public static final int LOTTO_NUMBER_MAX_LIMIT = 45;
    public static final int LOTTO_NUMBER_MIN_LIMIT = 1;
    public static final String NOT_PROPER_RANGE_NUMBERS_ERROR_MESSAGE =
        "로또 번호는" + LOTTO_NUMBER_MIN_LIMIT + "-" + LOTTO_NUMBER_MAX_LIMIT + "사이의 숫자이어야 합니다.";

    private final int number;

    public LottoNumber(final int number) {
        if (!isProperRange(number)) {
            throw new LottoCustomException(NOT_PROPER_RANGE_NUMBERS_ERROR_MESSAGE);
        }
        this.number = number;
    }

    private boolean isProperRange(int number) {
        return number >= LOTTO_NUMBER_MIN_LIMIT && number <= LOTTO_NUMBER_MAX_LIMIT;
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
