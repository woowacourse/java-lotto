package com.woowacourse.lotto.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Lotto {
    public static final int LOTTO_NUMS = 6;
    public static final int LOTTO_MIN = 1;
    public static final int LOTTO_MAX = 45;

    private SortedSet<Integer> nums;

    public Lotto(Set<Integer> nums) {
        this.nums = new TreeSet<>(nums);

        if (nums.size() != LOTTO_NUMS) {
            throw new IllegalArgumentException("로또 숫자는 6개여야 합니다.");
        }
        if (!nums.stream().allMatch(this::isValidRange)) {
            throw new IllegalArgumentException("올바르지 않은 범위의 숫자가 있습니다.");
        }
    }

    private boolean isValidRange(int n) {
        return LOTTO_MIN <= n && n <= LOTTO_MAX;
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
        return new StringBuilder("Lotto [ ")
            .append(nums.stream().map(String::valueOf).collect(Collectors.joining(", ")))
            .append(" ]")
            .toString();
    }
}
