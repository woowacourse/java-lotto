package com.woowacourse.lotto.domain;

import java.util.Objects;
import java.util.function.Consumer;

public class Lotto {
    public static final int UNIT_PRICE = 1000;

    private LottoNumber nums;

    public Lotto(LottoNumber nums) {
        this.nums = nums;
    }

    public int countMatch(LottoNumber otherNumber) {
        return nums.countMatch(otherNumber);
    }

    public boolean contains(int n) {
        return nums.contains(n);
    }

    public void forEachNums(Consumer<Integer> consumer) {
        nums.forEachNumbers(consumer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(nums, lotto.nums);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nums);
    }

    @Override
    public String toString() {
        return new StringBuilder("Lotto { nums: ")
            .append(nums)
            .append(" }")
            .toString();
    }
}
