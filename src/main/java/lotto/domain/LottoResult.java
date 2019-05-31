package lotto.domain;

import java.util.*;

public class LottoResult {
    private static final int PERCENT = 100;

    private Map<Rank, Integer> results = new TreeMap<>();

    LottoResult(List<Rank> ranks) {
        for (Rank rank : Rank.values()) {
            results.put(rank, 0);
        }
        generateResult(ranks);
    }

    public static LottoResult generateLottoResult(Lottos lottos, WinningLotto winningLotto) {
        List<Rank> ranks = lottos.match(winningLotto);
        return new LottoResult(ranks);
    }

    private void generateResult(List<Rank> ranks) {
        for (Rank rank : ranks) {
            results.put(rank, results.get(rank) + 1);
        }
    }

    public Map<Rank, Integer> getResults() {
        return Collections.unmodifiableMap(this.results);
    }

    public double findYield(int price) {
        return findWinningMoney() / price * PERCENT;
    }

    private double findWinningMoney() {
        long winningMoney = 0;
        for (Rank rank : Rank.values()) {
            winningMoney += (rank.getWinningMoney()) * results.get(rank);
        }
        return winningMoney;
    }

}
