package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMoneyTest {

    @DisplayName("로또 구입 금액이 1000원 미만인 예외")
    @Test
    public void lottoMoneyAmount() {
        int amount = 200;

        assertThatThrownBy(() -> new LottoMoney(amount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 구입 금액이 1000원 단위가 아닌 예외")
    @Test
    public void lottoMoneyUnit() {
        int amount = 1200;

        assertThatThrownBy(() -> new LottoMoney(amount))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
