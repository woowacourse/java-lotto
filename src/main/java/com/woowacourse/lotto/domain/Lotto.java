package com.woowacourse.lotto.domain;

import java.util.Objects;
import java.util.function.Consumer;

public class Lotto {
    public static final int UNIT_PRICE = 1000;

    private LottoNumberGroup nums;

    public Lotto(LottoNumberGroup nums) {
        this.nums = nums;
    }

    public int countMatch(LottoNumberGroup otherNumber) {
        return nums.countMatch(otherNumber);
    }

    public boolean contains(LottoNumber n) {
        return nums.contains(n);
    }

    public void forEachNums(Consumer<LottoNumber> consumer) {
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
