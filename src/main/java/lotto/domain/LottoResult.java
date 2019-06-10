package lotto.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.TreeMap;

public class LottoResult {
    private static final int LOTTO_MONEY = 1000;
    private final Map<Rank, Integer> results;

    public LottoResult(Map<Rank, Integer> results) {
        this.results = results;
    }

    public LottoResult() {
        this.results = new TreeMap<>();
        for (Rank rank : Rank.values()) {
            results.put(rank, 0);
        }
    }

    public void plus(Rank rank) {
        results.put(rank, results.get(rank) + 1);
    }

    public BigDecimal summury() {
        int sumOfRank = 0;
        int sumOfTickets = 0;
        for (Map.Entry<Rank, Integer> entry : results.entrySet()) {
            sumOfRank += entry.getKey().money() * entry.getValue();
            sumOfTickets += entry.getValue();
        }
        return new BigDecimal(sumOfRank)
                .divide(new BigDecimal(sumOfTickets), 3, RoundingMode.CEILING)
                .divide(new BigDecimal(LOTTO_MONEY), 3, RoundingMode.CEILING);
    }

    public Map<Rank, Integer> results() {
        return results;
    }
}
