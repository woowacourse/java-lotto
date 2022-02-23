package lotterymachine.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LotteryCalculatorTest {

    @Test
    @DisplayName("구입 금액에서 최대한으로 구매할 수 있는 로또 개수 계산")
    void calculateMaximumLottery() {
        int input = 15300;
        int lottery = LotteryCalculator.divideByLotteryPrice(input);
        assertThat(lottery).isEqualTo(15);
    }
}
