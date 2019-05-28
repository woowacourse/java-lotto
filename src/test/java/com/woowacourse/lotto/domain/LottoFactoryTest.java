package com.woowacourse.lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoFactoryTest {
    @Test
    void createLotto() {
        List<Integer> nums = Arrays.asList(25, 4, 15, 42, 32, 22);
        Lotto l = LottoFactory.createLotto(new TestNumberGenerator(nums));
        assertThat(l).isEqualTo(new Lotto(new HashSet<>(nums)));
    }

    @Test
    void createWithSet() {
        List<Integer> nums = Arrays.asList(25, 4, 15, 42, 32, 22);
        Lotto l = LottoFactory.createLotto(new HashSet<>(nums));
        assertThat(l).isEqualTo(new Lotto(new HashSet<>(nums)));
    }
}
