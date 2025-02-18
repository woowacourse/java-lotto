package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMoneyFactoryTest {

    @DisplayName("로또 구입 금액이 숫자가 아닌 예외")
    @Test
    public void lottoMoneyNumber() {
        String amountInput = "천";
        LottoMoneyFactory lottoMoneyFactory = new LottoMoneyFactory();

        assertThatThrownBy(() -> lottoMoneyFactory.createLottoMoney(amountInput))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
