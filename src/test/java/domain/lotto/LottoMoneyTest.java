package domain.lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import lotto.domain.lotto.LottoMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMoneyTest {

    @Test
    @DisplayName("로또 금액 1,000 이상일때 생성된다.")
    void testCreateLottoMoney() {
        assertThat(new LottoMoney(1000).getValue()).isEqualTo(1000);
    }

}