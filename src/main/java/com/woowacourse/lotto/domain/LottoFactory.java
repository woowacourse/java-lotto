package com.woowacourse.lotto.domain;

import java.util.HashSet;
import java.util.Set;

public class LottoFactory {

    public static Lotto createLotto(NumberGenerator numberGenerator) {
        Set<Integer> nums = new HashSet<>();
        while (nums.size() < LottoNumberGroup.LOTTO_NUMBER_SIZE) {
            nums.add(numberGenerator.generate());
        }
        return new Lotto(LottoNumberGroup.of(nums));
    }

    public static Lotto createLotto(LottoNumberGroup nums) {
        return new Lotto(nums);
    }
}
