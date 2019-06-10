package com.woowacourse.lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoQuantityTest {
    @Test
    void create() {
        assertThat(LottoQuantity.of(14)).isEqualTo(LottoQuantity.of(14));
    }

    @Test
    void createWithNegative() {
        assertThrows(IllegalArgumentException.class, () -> LottoQuantity.of(-1));
    }

    @Test
    void createWithString() {
        assertThat(LottoQuantity.of("2")).isEqualTo(LottoQuantity.of(2));
    }

    @Test
    void compareWithQuantity() {
        assertThat(LottoQuantity.of(14).compareTo(LottoQuantity.of(15))).isEqualTo(-1);
    }
}
