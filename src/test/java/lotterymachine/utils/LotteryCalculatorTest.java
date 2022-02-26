package lotterymachine.utils;

import lotterymachine.model.Count;
import lotterymachine.model.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LotteryCalculatorTest {

    @Test
    @DisplayName("구입 금액에서 최대한으로 구매할 수 있는 로또 개수 계산한다.")
    void calculateMaximumLottery() {
        Money input = new Money(15300);
        Count lottery = LotteryCalculator.divideByLotteryPrice(input);
        assertThat(lottery.getNumber()).isEqualTo(15);
    }

    @Test
    @DisplayName("당첨금액, 구입금액을 입력하여 수익률을 계산한다.")
    void calculateProfitRate() {
        Money amount = new Money(15000);
        Money winningLotteryAmount = new Money(1000);
        double result = LotteryCalculator.calculateProfitRate(winningLotteryAmount, amount);
        assertThat(result).isEqualTo(0.06);
    }
}
