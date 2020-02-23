package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EarningRateTest {
    @Test
    @DisplayName("수익율이 제대로 나오는지 확인하는 테스트")
    void correct_earning_rate_test() {
        PurchaseAmount purchaseAmount = new PurchaseAmount("1000");
        List<WinningRank> winningRanks = new ArrayList<>();
        winningRanks.add(WinningRank.FIFTH_RANK);
        EarningRate earningRate = new EarningRate(winningRanks, purchaseAmount);
        assertThat(earningRate.getEarningRate()).isEqualTo(500);
    }
}
