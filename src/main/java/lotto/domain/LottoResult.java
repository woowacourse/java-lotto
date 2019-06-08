package lotto.domain;

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

    private double summury() {
        double sumOfRank = 0.0;
        int sumOfTickets = 0;
        for (Map.Entry<Rank, Integer> entry : results.entrySet()) {
            sumOfRank += entry.getKey().money() * entry.getValue();
            sumOfTickets += entry.getValue();
        }
        return sumOfRank / sumOfTickets / LOTTO_MONEY;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("당첨통계\n").append("---------\n");
        for (Map.Entry<Rank, Integer> entry : results.entrySet()) {
            stringBuilder.append(entry.getKey().toString()).append(" - ").append(entry.getValue()).append("개\n");
        }
        stringBuilder.append("총 수익률은 ").append(summury()).append(" 입니다.");
        return stringBuilder.toString();
    }
}
