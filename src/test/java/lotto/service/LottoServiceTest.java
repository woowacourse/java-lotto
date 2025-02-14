package lotto.service;

import lotto.domain.LottoRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoServiceTest {

    private final LottoService lottoService = new LottoService();

    @Test
    @DisplayName("구매 금액보다 당첨 금액이 클 경우, 1보다 큰 수익률을 얻는다.")
    void makeSurplus() {
        Map<LottoRank, Integer> winningInfo = new HashMap<>();
        winningInfo.put(LottoRank.FIFTH_PLACE, 3);
        long purchaseAmount = 5000;
        double actualProfitRate = lottoService.calculateProfitRate(winningInfo, purchaseAmount);
        double expectedProfitRate = 3.0;

        assertThat(actualProfitRate).isEqualTo(expectedProfitRate);
    }

    @Test
    @DisplayName("구매 금액보다 당첨 금액이 작을 경우, 1보다 작은 수익률을 얻는다.")
    void makeDeficit() {
        Map<LottoRank, Integer> winningInfo = new HashMap<>();
        winningInfo.put(LottoRank.FIFTH_PLACE, 3);
        long purchaseAmount = 30000;
        double actualProfitRate = lottoService.calculateProfitRate(winningInfo, purchaseAmount);
        double expectedProfitRate = 0.5;

        assertThat(actualProfitRate).isEqualTo(expectedProfitRate);
    }
}
