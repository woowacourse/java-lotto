package lotto.domain;

import lotto.exception.LottoCounterException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoCounterTest {

    @Test
    @DisplayName("수동으로 구매할 로또 수가 음수인 경우 에러 발생")
    void checkManualLottoCountNegativeNumber() {
        Assertions.assertThatThrownBy(() -> new LottoCounter(new LottoPurchaseMoney(10000), -1))
                .isInstanceOf(LottoCounterException.class)
                .hasMessage("구매할 로또 수가 음수여서는 안 됩니다.");
    }

    @Test
    @DisplayName("수동으로 구매할 로또 수가 구입금액을 초과하는 경우 에러 발생")
    void checkManualLottoCountOverMoney() {
        Assertions.assertThatThrownBy(() -> new LottoCounter(new LottoPurchaseMoney(10000), 11))
                .isInstanceOf(LottoCounterException.class)
                .hasMessage("구매할 로또 수가 구입금액을 초과합니다.");
    }

    @Test
    @DisplayName("수동으로 구매할 로또 수에 따른 자동 구매 로또 수를 계산")
    void calculateAutoLottoCount() {
        LottoCounter lottoCounter = new LottoCounter(new LottoPurchaseMoney(10000), 3);
        Assertions.assertThat(lottoCounter.getAutoLottoCount())
                .isEqualTo(7);
    }
}
