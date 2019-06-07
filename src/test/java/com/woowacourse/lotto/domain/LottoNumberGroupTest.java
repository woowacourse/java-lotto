package com.woowacourse.lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberGroupTest {

    @Test
    void create() {
        Set<Integer> nums = new HashSet<>(Arrays.asList(3, 5, 1, 2, 6, 8));
        assertThat(LottoNumberGroup.of(nums)).isEqualTo(LottoNumberGroup.of(nums));
    }

    @Test
    void numsDuplicate() {
        assertThrows(IllegalArgumentException.class, () -> LottoNumberGroup.of(Arrays.asList(3, 12, 12, 6, 38, 42)));
    }

    @Test
    void invalidSize() {
        assertThrows(IllegalArgumentException.class, () -> LottoNumberGroup.of(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
        assertThrows(IllegalArgumentException.class, () -> LottoNumberGroup.of(Arrays.asList(1, 2, 3, 4, 5)));
    }

    @Test
    void invalidRange() {
        assertThrows(IllegalArgumentException.class, () -> LottoNumberGroup.of(Arrays.asList(1, 15, 25, 46, 32, 22)));
        assertThrows(IllegalArgumentException.class, () -> LottoNumberGroup.of(Arrays.asList(1, 15, 25, 44, 32, 0)));
        assertThrows(IllegalArgumentException.class, () -> LottoNumberGroup.of(Arrays.asList(1, 15, 25, 44, 32, -1)));
    }

    @Test
    void contains() {
        LottoNumberGroup lottoNumberGroup = LottoNumberGroup.of(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(lottoNumberGroup.contains(LottoNumber.of(5))).isTrue();
        assertThat(lottoNumberGroup.contains(LottoNumber.of(45))).isFalse();
    }

    @Test
    void countMatch() {
        assertThat(LottoNumberGroup.of(Arrays.asList(1, 5, 11, 15, 2, 33)).countMatch(LottoNumberGroup.of(Arrays.asList(5, 15, 25, 33, 42, 44))))
            .isEqualTo(3);
    }
}
