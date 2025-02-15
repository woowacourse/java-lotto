package model;

import constant.ErrorMessage;
import java.util.Random;

public class LottoNumber implements Comparable<LottoNumber> {
    public static final int MINIMUM_LOTTO_NUMBER = 1;
    public static final int MAXIMUM_LOTTO_NUMBER = 45;
    private final int number;

    public LottoNumber(int number) {
        validateBound(number);
        this.number = number;
    }

    public LottoNumber() {
        Random random = new Random();
        this.number = random.nextInt(MAXIMUM_LOTTO_NUMBER) + 1;
    }

    private void validateBound(int number) {
        if (number < MINIMUM_LOTTO_NUMBER || number > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_BOUND_EXCEPTION);
        }
    }

    public int getNumber() {
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

        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public int compareTo(LottoNumber o) {
        return this.number - o.getNumber();
    }
}
