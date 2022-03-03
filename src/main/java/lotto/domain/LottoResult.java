package lotto.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class LottoResult {

    private final Map<Rank, Long> ranks;

    public LottoResult(Map<Rank, Long> ranks) {
        this.ranks = new EnumMap<>(ranks);
    }

    public double calculateYield(Money money) {
        return getTotalPrizeMoney() / money.getAmount();
    }

    private double getTotalPrizeMoney() {
        return ranks.keySet()
                .stream()
                .mapToDouble(this::calculatePrizeMoney)
                .sum();
    }

    private long calculatePrizeMoney(Rank rank) {
        return rank.getPrizeMoney() * ranks.get(rank);
    }

    public Map<Rank, Long> getRanks() {
        return Collections.unmodifiableMap(ranks);
    }
}
