package lotto.domain;

import java.util.Objects;

import lotto.exceptions.LottoNumberException;

public class Number implements Comparable<Number> {
    private static final String INVALID_LOTTO_NUMBER = "로또 번호의 범위는 1-45 입니다.";

    private final int number;

    protected Number(int number) {
        valid(number);
        this.number = number;
    }


    public void valid(int lottoNumber) {
        if (lottoNumber < 0 || lottoNumber > 45) {
            throw new LottoNumberException(INVALID_LOTTO_NUMBER);
        }
    }

    public String getNumber() {
        return String.valueOf(number);
    }

    @Override
    public int compareTo(Number o) {
        return Integer.compare(this.number, o.number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number that = (Number) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
