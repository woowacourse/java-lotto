package com.woowacourse.lotto.domain;

import java.util.Collection;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class LottoNumberGroup {
    public static final int LOTTO_NUMBER_SIZE = 6;
    public static final int LOTTO_NUMBER_MIN = 1;
    public static final int LOTTO_NUMBER_MAX = 45;

    private final SortedSet<Integer> nums;

    private LottoNumberGroup(Collection<Integer> nums) {
        this.nums = new TreeSet<>(nums);
        if (this.nums.size() != 6) {
            throw new IllegalArgumentException("로또 숫자는 중복되지 않는 숫자로 6개여야 합니다.");
        }

        if (this.nums.stream()
            .anyMatch(this::isInvalidRange)) {
            throw new IllegalArgumentException("로또 숫자는 1 ~ 45 사이의 숫자여야 합니다.");
        }
    }

    private boolean isInvalidRange(int n) {
        return n < 1 || n > 45;
    }

    public static LottoNumberGroup of(Collection<Integer> nums) {
        return new LottoNumberGroup(nums);
    }

    public boolean contains(int n) {
        return nums.contains(n);
    }

    public void forEachNumbers(Consumer<Integer> consumer) {
        this.nums.forEach(consumer);
    }

    public int countMatch(LottoNumberGroup other) {
        return (int) this.nums.stream()
            .filter(other::contains)
            .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumberGroup that = (LottoNumberGroup) o;
        return Objects.equals(nums, that.nums);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nums);
    }

    @Override
    public String toString() {
        return String.format("LottoNumberGroup [%s]",
            nums.stream().map(String::valueOf).collect(Collectors.joining(", ")));
    }
}
