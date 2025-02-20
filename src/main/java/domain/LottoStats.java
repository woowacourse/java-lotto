package domain;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoStats {
    private final Map<Rank, Integer> rank;
    private final WinningLotto winningLotto;

    public LottoStats(WinningLotto winningLotto) {
        rank = new HashMap<>();
        this.winningLotto = winningLotto;
    }

    public void calculateResult(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            Rank lottoRank = lotto.getRank(winningLotto);
            rank.put(lottoRank, rank.getOrDefault(lottoRank, 0) + 1);
        }
    }

    private long getTotalPrize() {
        long totalPrize = 0L;
        for (Rank lottoRank : rank.keySet()) {
            totalPrize += lottoRank.getPrize() * rank.get(lottoRank);
        }
        return totalPrize;
    }

    public int getStatus(Rank lottoRank) {
        return rank.getOrDefault(lottoRank, 0);
    }

    public Double getEarningRate(int purchaseAmount) {
        return Math.floor(1. * getTotalPrize() / purchaseAmount * 100) / 100;
    }
}
