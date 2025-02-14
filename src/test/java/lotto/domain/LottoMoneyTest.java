package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMoneyTest {

    @DisplayName("로또 구입 금액이 숫자가 아닌 예외")
    @Test
    public void lottoMoneyNumber() {
        String input = "money";
        assertThatThrownBy(() -> new LottoMoney(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 구입 금액이 1000원 미만인 예외")
    @Test
    public void lottoMoneyAmount() {
        String input = "200";
        assertThatThrownBy(() -> new LottoMoney(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 구입 금액이 1000원 단위가 아닌 예외")
    @Test
    public void lottoMoneyUnit() {
        String input = "1200";
        assertThatThrownBy(() -> new LottoMoney(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
