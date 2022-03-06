package domain;

import java.util.*;

public class LottoResult {
    private final List<Rank> ranks;

    public LottoResult(List<Rank> ranks) {
        this.ranks = new ArrayList<>(ranks);
    }

    public Map<Rank, Integer> countRank() {
        Map<Rank, Integer> rankCounts = new LinkedHashMap<>();
        Arrays.asList(Rank.values()).stream()
                .filter(rank -> !rank.isNothing())
                .forEach(rank -> rankCounts.put(rank, count(rank)));
        return Collections.unmodifiableMap(rankCounts);
    }

    private int count(Rank rank) {
        return Collections.frequency(ranks, rank);
    }

    public double calculateProfitRate(Payment payment) {
        return calculateTotalProfit() / payment.calculateTicketPayment();
    }

    private double calculateTotalProfit() {
        return ranks.stream()
                .mapToDouble(Rank::getMoney)
                .sum();
    }
}
