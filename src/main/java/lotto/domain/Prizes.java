package lotto.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Prizes {

    private final Map<Rank, Integer> results = new LinkedHashMap<>();

    public Prizes(List<Rank> prizes) {
        for (Rank rank : Rank.values()) {
            results.put(rank,0);
        }
        matchRanks(prizes);
    }

    private void matchRanks(List<Rank> prizes) {
        for (Rank rank : prizes) {
            results.put(rank, results.get(rank) +1);
        }
    }

    public double calculateProfit(Money money) {
        int sum = 0;
        for (Rank rank : results.keySet()) {
            sum += rank.calculateTotalProfit(results.get(rank));
        }
        return money.calculateProfit(sum);
    }

    public Map<Rank, Integer> getResults() {
        return results;
    }

}
