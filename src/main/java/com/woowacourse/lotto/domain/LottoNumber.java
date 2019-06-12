package com.woowacourse.lotto.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    public static final int LOTTO_NUMBER_MIN = 1;
    public static final int LOTTO_NUMBER_MAX = 45;
    public static final Map<Integer, LottoNumber> cache = new HashMap<>();

    static {
        for (int i = LOTTO_NUMBER_MIN; i <= LOTTO_NUMBER_MAX; i++) {
            cache.put(i, new LottoNumber(i));
        }
    }

    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber of(int num) {
        LottoNumber got = cache.get(num);
        if (got == null) {
            throw new IllegalArgumentException(String.format("로또 숫자는 %d ~ %d 사이의 숫자여야 합니다.",
                LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX));
        }

        return got;
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
