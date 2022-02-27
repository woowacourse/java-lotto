package lotto.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class LottoResult {

    private static final int FLOOR_STANDARD = 100;
    private static final double FLOOR_AFTER_TREATMENT = 100.0;

    private final Map<Rank, Long> ranks;

    public LottoResult(Map<Rank, Long> ranks) {
        this.ranks = new EnumMap<>(ranks);
    }

    public double calculateYield(Money money) {
        return Math.floor(getTotalPrizeMoney() / money.getPrice() * FLOOR_STANDARD) / FLOOR_AFTER_TREATMENT;
    }

    private double getTotalPrizeMoney() {
        return ranks.keySet()
                .stream()
                .mapToDouble(Rank::getPrizeMoney)
                .sum();
    }

    public Map<Rank, Long> getRanks() {
        return Collections.unmodifiableMap(ranks);
    }
}
