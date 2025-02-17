package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.view.InputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMoneyTest {

    @DisplayName("로또 구입 금액이 숫자가 아닌 예외")
    @Test
    public void lottoMoneyDigit() {
        String input = "money";
        assertThatThrownBy(() -> InputView.validateAndParseLottoMoney(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 구입 금액이 1000원 미만인 예외")
    @Test
    public void lottoMoneyAmount() {
        assertThatThrownBy(() -> new LottoMoney(200))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 구입 금액이 1000원 단위가 아닌 예외")
    @Test
    public void lottoMoneyUnit() {
        assertThatThrownBy(() -> new LottoMoney(1200))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
