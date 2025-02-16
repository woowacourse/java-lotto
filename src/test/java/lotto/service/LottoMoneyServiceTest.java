package lotto.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMoneyServiceTest {

    @DisplayName("로또 구입 금액이 숫자가 아닌 예외")
    @Test
    public void lottoMoneyNumber() {
        String amountInput = "천";
        LottoMoneyService lottoMoneyService = new LottoMoneyService();

        assertThatThrownBy(() -> lottoMoneyService.createLottoMoney(amountInput))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
