package com.woowacourse.lotto.domain;

import java.util.List;

public class TestNumberGenerator implements NumberGenerator {
    private final List<Integer> nums;
    private int current;

    public TestNumberGenerator(List<Integer> numsToGenerate) {
        this.nums = numsToGenerate;
        current = 0;
    }

    @Override
    public int generate() {
        return nums.get(current++);
    }
}
