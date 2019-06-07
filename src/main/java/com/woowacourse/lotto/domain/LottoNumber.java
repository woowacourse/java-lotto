package com.woowacourse.lotto.domain;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    public static final int LOTTO_NUMBER_MIN = 1;
    public static final int LOTTO_NUMBER_MAX = 45;

    private final int number;

    private LottoNumber(int number) {
        this.number = number;

        assertValidRange(number);
    }

    private void assertValidRange(int n) {
        if (n < 1 || n > 45) {
            throw new IllegalArgumentException("로또 숫자는 1 ~ 45 사이의 숫자여야 합니다.");
        }
    }

    public static LottoNumber of(int num) {
        return new LottoNumber(num);
    }

    public int toInt() {
        return number;
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
    public int compareTo(LottoNumber o) {
        return Integer.compare(number, o.number);
    }

    @Override
    public String toString() {
        return "LottoNumber { value: " + number + " }";
    }
}
