package domain;

import Lotto.domain.EarningRate;
import Lotto.domain.PurchaseAmount;
import Lotto.domain.Rank;
import Lotto.domain.Ranks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class EarningRateTest {
    @Test
    @DisplayName("수익률 계산이 잘 되는지 확인")
    void calculateEarningRate() {
        Ranks ranks = new Ranks(Arrays.asList(Rank.FOURTH, Rank.FIFTH));
        PurchaseAmount purchaseAmount = new PurchaseAmount(10000);
        EarningRate earningRate = new EarningRate(ranks, purchaseAmount);
        assertThat(earningRate.getEarningRate()).isEqualTo(450.0);
    }
}
