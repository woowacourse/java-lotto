package com.woowacourse.lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {

    @Test
    void create() {
        Set<Integer> nums = new HashSet<>(Arrays.asList(3, 5, 1, 2, 6, 8));
        assertThat(LottoNumber.of(nums)).isEqualTo(LottoNumber.of(nums));
    }

    @Test
    void numsDuplicate() {
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.of(Arrays.asList(3, 12, 12, 6, 38, 42)));
    }

    @Test
    void invalidSize() {
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.of(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.of(Arrays.asList(1, 2, 3, 4, 5)));
    }

    @Test
    void invalidRange() {
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.of(Arrays.asList(1, 15, 25, 46, 32, 22)));
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.of(Arrays.asList(1, 15, 25, 44, 32, 0)));
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.of(Arrays.asList(1, 15, 25, 44, 32, -1)));
    }

    @Test
    void contains() {
        LottoNumber lottoNumber = LottoNumber.of(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(lottoNumber.contains(5)).isTrue();
        assertThat(lottoNumber.contains(45)).isFalse();
    }

    @Test
    void countMatch() {
        assertThat(LottoNumber.of(Arrays.asList(1, 5, 11, 15, 2, 33)).countMatch(LottoNumber.of(Arrays.asList(5, 15, 25, 33, 42, 44))))
            .isEqualTo(3);
    }
}
