package lotto.domain;

import java.util.*;

public class LottoResult {

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

    public int getCountOfRanker(Rank rank) {
        return results.get(rank);
    }

    public double findYield(int price) {
        return findWinningMoney() / price * 100;
    }

    private double findWinningMoney() {
        return Arrays.stream(Rank.values())
                .mapToLong(rank -> rank.getWinningMoney() * results.get(rank))
                .sum();
    }

    @Override
    public String toString() {
        return "LottoResult{" +
                "results=" + results +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoResult that = (LottoResult) o;
        return Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(results);
    }

}
