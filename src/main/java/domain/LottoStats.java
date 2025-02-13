package domain;


import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LottoStats {
    private Map<Rank, Integer> rank;
    private final List<Integer> winningNumbers;
    private final int bonusBall;

    public LottoStats(List<Integer> winningNumbers, int bonusBall) {
        rank = new TreeMap<>();
        this.winningNumbers = winningNumbers;
        this.bonusBall = bonusBall;
    }

    public void calculateResult(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            Rank lottoRank = lotto.getRank(winningNumbers, bonusBall);
            rank.put(lottoRank, rank.getOrDefault(lottoRank, 0) + 1);
        }
    }

    public Long getTotalPrize() {
        Long totalPrize = 0L;
        for (Rank lottoRank : rank.keySet()) {
            totalPrize += lottoRank.getPrize() * rank.get(lottoRank);
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
        return rank.getOrDefault(lottoRank, 0);
    }

    public String getEarningRate(int purchaseAmount) {
        return String.format("%.2f", Math.floor(1. * getTotalPrize() / purchaseAmount * 100) / 100);
    }
}
