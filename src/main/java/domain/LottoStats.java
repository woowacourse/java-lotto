package domain;

import java.util.Map;

public class LottoStats {
    private static final int PERCENTAGE = 100;
    private static final int ROUND_DECIMAL = 100;

    private final Map<Rank, Integer> ranks;


    public LottoStats(Map<Rank,Integer> lottoRanks) {
        ranks = lottoRanks;
    }

    public long getTotalPrize() {
        long totalPrize = 0L;
        for (Rank lottoRank : ranks.keySet()) {
            totalPrize += lottoRank.getPrize() * ranks.get(lottoRank);
        }
        return totalPrize;
    }

    public int getStatusCount(Rank lottoRank) {
        return getRankCount(lottoRank);
    }

    public Integer getRankCount(Rank lottoRank) {
        return ranks.getOrDefault(lottoRank, Rank.DEFAULT_COUNT);
    }

    public String getEarningRate(int purchaseAmount) {
        return String.format("%.2f", Math.floor(1. * getTotalPrize() / purchaseAmount * PERCENTAGE) / ROUND_DECIMAL);
    }
}
