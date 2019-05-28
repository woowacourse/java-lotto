package com.woowacourse.lotto.domain;

import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {
    private final int min;
    private final int max;

    public RandomNumberGenerator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public int generate() {
        return new Random().nextInt(max - min + 1) + min;
    }
}
