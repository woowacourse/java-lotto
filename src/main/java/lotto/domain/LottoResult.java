package lotto.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class LottoResult {

    private final Map<Rank, Integer> ranks = new LinkedHashMap<>();

    public LottoResult() {
        initRanks();
    }

    private void initRanks() {
        for (int i = 1; i < Rank.values().length; i++) {
            ranks.put(Rank.values()[i], 0);
        }
    }

    public Map<Rank, Integer> matchRank(WinningLotto winningLotto, LottoGroup lottos) {
        for (Lotto lotto : lottos.getLottoGroup()) {
            Rank rank = winningLotto.findRank(lotto);
            ranks.computeIfPresent(rank, (key, val) -> val + 1);
        }
        return ranks;
    }

    public double findEarningRate(int money) {
        int sumOfPrize = 0;
        for (Map.Entry<Rank, Integer> singleCount : ranks.entrySet()) {
            sumOfPrize += singleCount.getKey().getPrize() * singleCount.getValue();
        }
        return (double) sumOfPrize / (double) money;
    }
}
