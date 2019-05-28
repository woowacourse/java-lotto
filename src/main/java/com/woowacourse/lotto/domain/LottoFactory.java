package com.woowacourse.lotto.domain;

import java.util.HashSet;
import java.util.Set;

public class LottoFactory {

    public static Lotto createLotto(NumberGenerator numberGenerator) {
        Set<Integer> nums = new HashSet<>();
        while (nums.size() < Lotto.LOTTO_NUMS) {
            nums.add(numberGenerator.generate());
        }
        return new Lotto(nums);
    }

    public static Lotto createLotto(Set<Integer> nums) {
        return new Lotto(nums);
    }
}
