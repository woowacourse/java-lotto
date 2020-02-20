package lotto.domain;

import lotto.domain.errors.ErrorMessage;
import lotto.exception.NotInScopeException;
import lotto.exception.NotNumberException;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private int number;

    public LottoNumber(String number) {
        validateNumber(number);
        validateNumberScope(Integer.parseInt(number));
        this.number = Integer.parseInt(number);
    }

    public LottoNumber(int number) {
        validateNumberScope(number);
        this.number = number;
    }

    private void validateNumber(String lottoNumber) {
        try {
            Integer.parseInt(lottoNumber);
        } catch (NumberFormatException e) {
            throw new NotNumberException(ErrorMessage.NOT_NUMBER.getMessage());
        }
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

    private void validateNumberScope(int lottoNumber) {
        if (lottoNumber < MIN_LOTTO_NUMBER
                || lottoNumber > MAX_LOTTO_NUMBER) {
            throw new NotInScopeException(ErrorMessage.OVER_SCOPE.getMessage());
        }
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
        if (this.number > lottoNumber.number) {
            return 1;
        }
        if (this.number < lottoNumber.number) {
            return -1;
        }
        return 0;
    }
}

