package lotto.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Prizes {

    private final List<Prize> prizes;
    private final Map<Rank, Integer> results = new LinkedHashMap<>();

    public Prizes(List<Prize> prizes) {
        this.prizes = prizes;
        for (Rank rank : Rank.values()) {
            results.put(rank, 0);
        }
        matchRanks();
    }

    public double calculateProfit(Money money) {
        int sum = 0;
        for (Rank rank : results.keySet()) {
            sum += rank.calculateTotalProfit(results.get(rank));
        }
        return money.calculateProfit(sum);
    }

    private void matchRanks() {
        for (Prize prize : prizes) {
            Rank rank = prize.getMatchRank();
            results.put(rank, results.get(rank) + 1);
        }
    }

    public Map<Rank, Integer> getResults() {
        return new LinkedHashMap<>(results);
    }
}
