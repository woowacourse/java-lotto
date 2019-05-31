package lotto.domain;

import java.util.*;

public class LottoResult {
    private static final int PERCENT = 100;
    private Map<Rank, Integer> results = new TreeMap<>();

    public LottoResult(List<Rank> ranks) {
        for (Rank rank : Rank.values()) {
            results.put(rank, 0);
        }
        generateResult(ranks);
    }

    public Map<Rank, Integer> getResults() {
        return Collections.unmodifiableMap(this.results);
    }

    private void generateResult(List<Rank> ranks) {
        for (Rank rank : ranks) {
            results.put(rank, results.get(rank) + 1);
        }
    }

    public double findYield(int price) {
        return findWinningMoney() / price * PERCENT;
    }

    private double findWinningMoney() {
        long winningMoney = 0;
        Set<Rank> ranks = results.keySet();
        for (Rank rank : ranks) {
            winningMoney += (rank.getWinningMoney()) * results.get(rank);
        }
        return winningMoney;
    }

}
