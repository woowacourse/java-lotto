package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import static lotto.domain.Money.PRICE_OF_LOTTO;

public class LottoGameResult {
    public static final int ADD_ONE = 1;

    private final Map<Rank, Integer> ranks;

    public LottoGameResult() {
        ranks = new EnumMap<>(Rank.class);
        initEnumMap();
    }

    private void initEnumMap() {
        Arrays.stream(Rank.values())
                .forEach(rank -> ranks.put(rank, 0));
    }

    public void add(Rank rank) {
        ranks.put(rank, countByRank(rank) + ADD_ONE);
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
                .mapToDouble(rank -> rank.getKey().getReward() * rank.getValue())
                .sum();
    }

    private int calculateTotalPrice() {
        int numberOfTotalLotto = ranks.values()
                .stream()
                .mapToInt(num -> num)
                .sum();
        return numberOfTotalLotto * PRICE_OF_LOTTO;
    }
}
