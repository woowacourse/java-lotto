package domain;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LottoResults {
    private final Map<Rank, Integer> results;

    public LottoResults(Map<Rank, Integer> results) {
        this.results = results;
    }

    public static LottoResults of(WinningNumbers winningNumbers, Tickets tickets) {
        List<Rank> ranks = tickets.getRanks(winningNumbers);
        Map<Rank, Integer> results = new TreeMap<>(Comparator.comparingLong(Rank::getAmount));
        for (Rank rank : ranks) {
            results.merge(rank, 1, (value, putValue) -> value + 1);
        }
        return new LottoResults(results);
    }

    public long getProfit() {
        long profit = 0;
        for (Rank rank : results.keySet()) {
            profit += rank.getAmount() * results.get(rank);
        }
        return profit;
    }

    public Map<Rank, Integer> getLottoResults() {
        return results;
    }
}
