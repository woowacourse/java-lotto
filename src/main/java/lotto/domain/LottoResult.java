package lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class LottoResult {
    private Map<Rank, Integer> results = new TreeMap<>();

    public LottoResult(List<Rank> ranks) {
        for (Rank rank : Rank.values()) {
            results.put(rank, 0);
        }
        generateResult(ranks);
    }

    private void generateResult(List<Rank> ranks) {
        for (Rank rank : ranks) {
            results.put(rank, results.get(rank) + 1);
        }
    }

    public double findYield(int price) {
        return findWinningMoney() / price * 100;
    }

    private double findWinningMoney() {
        long winningMoney = 0;
        Set<Rank> ranks = results.keySet();
        for (Rank rank : ranks) {
            winningMoney += (rank.getWinningMoney()) * results.get(rank);
        }
        return winningMoney;
    }

    public Map<Rank, Integer> getResults() {
        return new TreeMap<>(results);
    }

}
