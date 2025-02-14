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
            results.put(rank,0);
        }
        matchRanks();
    }

    public void matchRanks() {
        for (Prize prize : prizes) {
            Rank rank = prize.matchRank();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Rank rank : Rank.validRank()) {
            sb.append(rank.getMessage())
                    .append(results.get(rank))
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }
}
