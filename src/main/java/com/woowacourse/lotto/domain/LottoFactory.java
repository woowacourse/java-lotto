package com.woowacourse.lotto.domain;

import java.util.HashSet;
import java.util.Set;

public class LottoFactory {

    public static Lotto createLotto(NumberGenerator numberGenerator) {
        Set<Integer> nums = new HashSet<>();
        while (nums.size() < LottoNumber.LOTTO_NUMBER_SIZE) {
            nums.add(numberGenerator.generate());
        }
        return new Lotto(LottoNumber.of(nums));
    }

    public static Lotto createLotto(LottoNumber nums) {
        return new Lotto(nums);
    }
}
