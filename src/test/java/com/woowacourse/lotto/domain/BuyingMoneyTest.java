package com.woowacourse.lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BuyingMoneyTest {
    @Test
    void create() {
        assertThat(new BuyingMoney(3400)).isEqualTo(new BuyingMoney(3400));
    }

    @Test
    void invalidRange() {
        assertThrows(IllegalArgumentException.class, () -> new BuyingMoney(-1000));
    }

    @Test
    void quantity() {
        assertThat(new BuyingMoney(3500).getQuantity()).isEqualTo(LottoQuantity.of(3));
        assertThat(new BuyingMoney(3000).getQuantity()).isEqualTo(LottoQuantity.of(3));
    }
}
