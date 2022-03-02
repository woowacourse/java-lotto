package lotto.domain;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int LOTTO_MIN_RANGE = 1;
    private static final int LOTTO_MAX_RANGE = 45;
    private static final String ERROR_WRONG_LOTTO_NUMBER = "[ERROR] " + LOTTO_MIN_RANGE + "~" + LOTTO_MAX_RANGE + " 사이의 숫자를 입력해주세요.";

    private final int lottoNumber;

    public LottoNumber(final int number) {
        checkValidLottoRange(number);
        this.lottoNumber = number;
    }

    public LottoNumber(final String number) {
        checkValidLottoNumber(number);
        this.lottoNumber = Integer.parseInt(number);
    }

    private void checkValidLottoNumber(final String number) {
        checkValidInt(number);
        checkValidLottoRange(Integer.parseInt(number));
    }

    private void checkValidInt(final String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ERROR_WRONG_LOTTO_NUMBER);
        }
    }

    private void checkValidLottoRange(final int number) {
        if (!(number >= LOTTO_MIN_RANGE && number <= LOTTO_MAX_RANGE)) {
            throw new IllegalArgumentException(ERROR_WRONG_LOTTO_NUMBER);
        }
    }

    public int getLottoNumber() {
        return lottoNumber;
    }

    @Override
    public int compareTo(LottoNumber o) {
        return lottoNumber - o.lottoNumber;
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
        return lottoNumber == that.lottoNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }
}
