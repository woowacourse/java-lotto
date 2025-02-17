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

    public String toString() {
        StringBuilder stats = new StringBuilder();
        for (Rank lottoRank : Rank.values()) {
            stats.append(getStatus(lottoRank));
        }
        return stats.toString();
    }

    private String getStatus(Rank lottoRank) {
        if (lottoRank == Rank.NONE) return "";
        return lottoRank.getMessage() +
                getRankCount(lottoRank) + "개\n";
    }

    public Integer getRankCount(Rank lottoRank) {
        return ranks.getOrDefault(lottoRank, Rank.DEFAULT_COUNT);
    }

    public String getEarningRate(int purchaseAmount) {
        return String.format("%.2f", Math.floor(1. * getTotalPrize() / purchaseAmount * PERCENTAGE) / ROUND_DECIMAL);
    }
}
