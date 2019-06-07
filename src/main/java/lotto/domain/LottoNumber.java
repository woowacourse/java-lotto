package lotto.domain;

import lotto.domain.exception.LottoNumberOfOverRangeException;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_LOTTO_NUMBER = 1;

    private final int number;

    public LottoNumber() {
        this(createRandomNumber());
    }

    public LottoNumber(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new LottoNumberOfOverRangeException("로또 번호는 1부터 45까지 입니다.");
        }
        this.number = number;
    }

    private static int createRandomNumber() {
        return (int) (Math.random() * MAX_LOTTO_NUMBER) + MIN_LOTTO_NUMBER;
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
        return String.valueOf(number);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(number, o.number);
    }
}
