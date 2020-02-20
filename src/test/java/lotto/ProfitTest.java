package lotto;

import domain.Profit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfitTest {
    @Test
    void 수익_누적_테스트() {
        Profit profit = new Profit();
        profit.addWinningMoney(50000);
        assertThat(profit.getProfit()).isEqualTo(50000);
        profit.addWinningMoney(30000000);
        assertThat(profit.getProfit()).isEqualTo(30050000);
    }
}
