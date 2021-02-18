package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LottoGameResult {
    private final Map<Rank, Integer> ranks;

    public LottoGameResult() {
        ranks = new HashMap<>();
        initEnumMap();
    }

    private void initEnumMap() {
        Arrays.stream(Rank.values())
                .forEach(rank -> ranks.put(rank, 0));
    }

    public void add(Rank rank) {
        ranks.put(rank, ranks.get(rank) + 1);
    }

    public Map<Rank, Integer> ranks() {
        return Collections.unmodifiableMap(ranks);
    }

    public int countByRank(Rank rank) {
        return ranks.get(rank);
    }

    public double calculateProfit() {
        return totalReward() / calculateTotalPrice();
    }

    private double totalReward() {
        return ranks.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getReward() * entry.getValue())
                .sum();
    }

    private int calculateTotalPrice() {
        int numberOfTotalLotto = ranks.values()
                .stream()
                .mapToInt(num -> num)
                .sum();
        return numberOfTotalLotto * Money.PRICE_OF_LOTTO;
    }
}
