package domain.lotto;

import static lotto.domain.lotto.LottoMoney.LOTTO_PURCHASE_MONEY_LACK_ERROR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import lotto.domain.lotto.LottoMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMoneyTest {

    @Test
    @DisplayName("로또 금액 1,000 이상일때 생성된다.")
    void testCreateLottoMoney() {
        assertThat(new LottoMoney(1000).getValue()).isEqualTo(1000);
    }

    @Test
    @DisplayName("로또 금액은 1,000원 미만이면 예외가 발생한다.")
    void testCreateLottoMoneyException() {
        assertThatThrownBy(() -> new LottoMoney(500))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(LOTTO_PURCHASE_MONEY_LACK_ERROR);
    }

}