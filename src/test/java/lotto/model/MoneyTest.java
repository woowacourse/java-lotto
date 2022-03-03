package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("금액에 따른 로또 구입 개수 계산")
    void calculateLottoCountTest() {
        int amount = 100_100;
        Money money = new Money(amount);

        assertThat(money.purchase(Lotto.LOTTO_PRICE)).isEqualTo(100000 / Lotto.LOTTO_PRICE);
    }
}