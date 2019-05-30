package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MoneyTest {
    @Test
    void initTest() {
        assertThatThrownBy(() -> new Money(-255));
    }
    @Test
    void amountTest() {
        assertThat(new Money(65535).getAmount()).isEqualTo(65535);
    }

    @Test
    void lottoAmountTest() {
        assertThat(new Money(65535).getLottoPurchaseAmount()).isEqualTo(65);
    }

    @Test
    void earningRateTest() {
        assertThat(new Money(30000).getEarningRate(new Money(100))).isCloseTo(29900.0, offset(0.0000001));
    }
}