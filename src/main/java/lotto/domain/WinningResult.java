package lotto.domain;

import java.util.Collections;
import java.util.Map;

public class WinningResult {
    private static final int HUNDRED = 100;

    private final Map<LottoRank, Integer> result;

    public WinningResult(Map<LottoRank, Integer> result) {
        this.result = result;
    }

    public double calculateRevenueRate(int purchaseAmount) {
        return (sumTotalWinningAmount() / purchaseAmount) * HUNDRED;
    }

    int sumTotalWinningAmount() {
        int totalWinningAmount = 0;

        for (LottoRank lottoRank : result.keySet()) {
            totalWinningAmount += lottoRank.getWinningAmount() * result.get(lottoRank);
        }
        return totalWinningAmount;
    }

    public Map<LottoRank, Integer> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
