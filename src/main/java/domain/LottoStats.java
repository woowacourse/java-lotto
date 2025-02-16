package domain;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoStats {
    private final Map<Rank, Integer> rank;
    private final WinningLotto winnigLotto;

    public LottoStats(WinningLotto winnigLotto) {
        rank = new HashMap<>();
        this.winnigLotto = winnigLotto;
    }

    public void calculateResult(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            Rank lottoRank = lotto.getRank(winnigLotto);
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

    private Integer getRankCount(Rank lottoRank) {
        return rank.getOrDefault(lottoRank, 0);
    }

    public String getEarningRate(int purchaseAmount) {
        return String.format("%.2f", Math.floor(1. * getTotalPrize() / purchaseAmount * 100) / 100);
    }
}
