package lotto.domain.lottos.amount;

import lotto.domain.money.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @ParameterizedTest(name = "매개변수 중 Money 가 null 이면 NullPointerException을 발생시킨다.")
    @NullSource
    public void moneyIsNullTest(Money money) {
        assertThatThrownBy(() -> {
            new LottoAmount(money, manualAmount);
        }).isInstanceOf(NullPointerException.class).hasMessage(LottoAmount.MONEY_NULL_ERROR_MESSAGE);
    }

    @ParameterizedTest(name = "매개변수 중 ManualAmount 가 null 이면 NullPointerException을 발생시킨다.")
    @NullSource
    public void moneyIsNullTest(ManualAmount manualAmount) {
        assertThatThrownBy(() -> {
            new LottoAmount(money, manualAmount);
        }).isInstanceOf(NullPointerException.class).hasMessage(LottoAmount.MANUAL_AMOUNT_NULL_ERROR_MESSAGE);
    }
}