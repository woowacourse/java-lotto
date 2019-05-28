package com.woowacourse.lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTest {

    @Test
    void create() {
        Set<Integer> nums = new HashSet<>(Arrays.asList(2, 5, 13, 25, 42, 44));
        Assertions.assertThat(new Lotto(nums)).isEqualTo(new Lotto(nums));
    }

    @Test
    void equalsNotCareOrder() {
        Set<Integer> nums1 = new HashSet<>(Arrays.asList(2, 13, 32, 36, 39, 41));
        Set<Integer> nums2 = new HashSet<>(Arrays.asList(2, 32, 13, 36, 39, 41));
        assertThat(new Lotto(nums1)).isEqualTo(new Lotto(nums2));
    }

    @Test
    void createWithNumsOfInvalidSize() {
        Set<Integer> nums = new HashSet<>(Arrays.asList(2, 5, 13, 25, 42));
        assertThrows(IllegalArgumentException.class, () -> new Lotto(nums));
    }

    @Test
    void createWithOutOfRange() {
        Set<Integer> nums = new HashSet<>(Arrays.asList(-1, 2 , 25, 32, 44, 42));
        assertThrows(IllegalArgumentException.class, () -> new Lotto(nums));
    }

    @Test
    void createWithDuplicateNums() {
        assertThrows(IllegalArgumentException.class, () -> new Lotto(new HashSet<>(Arrays.asList(1, 2, 2, 15, 22, 32))));

    }
}
