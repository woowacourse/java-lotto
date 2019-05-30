package lotto.domain;

import java.util.Map;

public class WinningResult {
    private final Map<LottoRank, Integer> result;

    public WinningResult(Map<LottoRank, Integer> result) {
        this.result = result;
    }

    public double calculateRevenueRate(int purchaseAmount) {
        return (sumTotalWinningAmount() / purchaseAmount) * 100;
    }

    // TODO depth check
    int sumTotalWinningAmount() {
        int totalWinningAmount = 0;

        for (LottoRank lottoRank : result.keySet()) {
            if (result.get(lottoRank) != 0) {
                totalWinningAmount += lottoRank.getWinningAmount() * result.get(lottoRank);
            }
        }
        return totalWinningAmount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (LottoRank lottoRank : result.keySet()) {
            sb.append(lottoRank.getCountOfMatch());
            sb.append("개 일치 ");
            sb.append("(" + lottoRank.getWinningAmount() + "원) - ");
            sb.append(result.get(lottoRank) + "개\n");
        }
        return sb.toString();
    }
}
