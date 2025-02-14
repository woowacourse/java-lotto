package domain;


import java.util.Map;
import java.util.TreeMap;

public class LottoStats {
    private final Map<Rank, Integer> ranks;

    public LottoStats() {
        ranks = new TreeMap<>();
    }

    public void addLottoRankCount(Rank lottoRank){
        ranks.put(lottoRank, ranks.getOrDefault(lottoRank, 0) + 1);
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
        return ranks.getOrDefault(lottoRank, 0);
    }

    public String getEarningRate(int purchaseAmount) {
        return String.format("%.2f", Math.floor(1. * getTotalPrize() / purchaseAmount * 100) / 100);
    }
}
