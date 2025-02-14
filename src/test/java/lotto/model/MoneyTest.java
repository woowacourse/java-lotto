package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @DisplayName("구매금액과 당첨 결과를 알려주면 수익률을 계산해준다.")
    @Test
    void calculateReturnRatio() {
        Money money = new Money(14_000);
        WinningResults responses = new WinningResults(
                List.of(new WinningResult(3, 5_000, false, 1)));

        assertThat(money.calculateReturnRatio(responses.calculateEarnedMoney())).isEqualTo(0.35);
    }

}
