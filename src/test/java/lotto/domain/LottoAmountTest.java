package lotto.domain;

import lotto.domain.money.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoAmountTest {

    private Money money;
    private ManualAmount manualAmount;

    @BeforeEach
    void setUp() {
        money = new Money("3000");
        manualAmount = new ManualAmount("2", money);
    }

    @Test
    @DisplayName("로또 수동 개수와 자동 개수를 가진 LottoAmount 생성된다.")
    public void createLottoAmountTest() {
        LottoAmount lottoAmount = new LottoAmount(money, manualAmount);

        assertThat(lottoAmount).isInstanceOf(LottoAmount.class);
    }
}