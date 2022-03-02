package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PrizeResultTest {

    private PrizeResult prizeResult;

    @BeforeEach
    void init() {
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(new Lotto(new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6))));
        lottos.add(new Lotto(new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 7))));
        lottos.add(new Lotto(new HashSet<>(Arrays.asList(1, 2, 3, 4, 6, 8))));

        String numbers = "1, 2, 3, 4, 5, 12";
        String bonus = "6";
        WinningNumbers winningNumber = new WinningNumbers(numbers, bonus);

        prizeResult = new PrizeResult(lottos, winningNumber);
    }

    @Test
    @DisplayName("올바른 상금이 계산된다.")
    void lottos_makeRightTotalPrize() {
        Map<Rank, Integer> prizeResult = this.prizeResult.getPrizeResult();

        int totalPrize = 0;
        for (Rank rank : prizeResult.keySet()) {
            totalPrize += (prizeResult.get(rank) * rank.getPrize());
        }

        assertThat(totalPrize).isEqualTo(31550000);
    }

    @Test
    @DisplayName("올바른 수익률이 계산된다.")
    void lottos_calculateEarningRate() {
        final int inputMoney = 3000;
        float earningRate = prizeResult.earningRate(inputMoney);

        assertThat(earningRate).isEqualTo(10516.66f);
    }

}
