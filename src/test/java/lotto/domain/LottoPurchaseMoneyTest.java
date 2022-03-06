package lotto.domain;

import lotto.exception.LottoPurchaseMoneyException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoPurchaseMoneyTest {

    @ParameterizedTest(name = "1000원 단위가 아닐 경우 예외 발생 - case : {0}")
    @ValueSource(ints = {100, 1200, 1234})
    void checkUnit(int input) {
        Assertions.assertThatThrownBy(() -> new LottoPurchaseMoney(input))
                .isInstanceOf(LottoPurchaseMoneyException.class)
                .hasMessage("구입금액은 1000원 단위만 가능합니다.");
    }

    @Test
    void getProfitRate() {
        LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney(14000);
        Assertions.assertThat(lottoPurchaseMoney.calculateProfitRate(5000)).isEqualTo(0.35);
    }
}
