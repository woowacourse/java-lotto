package lotto.domain;

import lotto.domain.exceptions.LottoNumberException;

import java.util.Objects;

public class LottoNumber {
    static final int MIN_NUMBER = 1;
    static final int MAX_NUMBER = 45;
    private final int number;

    public LottoNumber(int number) {
        validate(number);
        this.number = number;
    }

    private void validate(int number) {
        if (number > MAX_NUMBER || number < MIN_NUMBER) {
            throw new LottoNumberException();
        }
    }

    public int toInt() {
        return number;
    }

    public int compareTo(LottoNumber o) {
        return number - o.toInt();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
