package com.woowacourse.lotto.domain;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class LottoNumberGroup {
    public static final int LOTTO_NUMBER_SIZE = 6;

    private final SortedSet<LottoNumber> nums;

    private LottoNumberGroup(Collection<LottoNumber> nums) {
        this.nums = Collections.unmodifiableSortedSet(new TreeSet<>(nums));
        if (this.nums.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("로또 숫자는 중복되지 않는 숫자로 6개여야 합니다.");
        }
    }

    public static LottoNumberGroup of(Collection<Integer> nums) {
        return new LottoNumberGroup(nums.stream()
            .map(LottoNumber::of)
            .collect(Collectors.toList()));
    }

    public boolean contains(LottoNumber number) {
        return nums.contains(number);
    }

    public void forEachNumbers(Consumer<LottoNumber> consumer) {
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
