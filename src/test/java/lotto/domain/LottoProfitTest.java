package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoProfitTest {

    @DisplayName("로또의 수익률은 총 수익률 / 구입 금액으로 결정된다.")
    @Test
    void ofProfit() {
        Map<WinningResult, Integer> result = new HashMap<>();
        result.put(WinningResult.FIRST_PRIZE, 1);
        Money money = new Money(1000);

       assertThat(LottoProfit.ofProfit(result, money).getProfit())
               .isEqualTo(WinningResult.FIRST_PRIZE.getWinnings() / money.getMoney());
    }
}