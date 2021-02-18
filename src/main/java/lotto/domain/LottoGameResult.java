package lotto.domain;

import java.util.*;

public class LottoGameResult {
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
        ranks.put(rank, ranks.get(rank) + 1);
    }

    public double totalReward() {
        return ranks.entrySet().stream()
                .mapToDouble(rank -> rank.getKey().getReward() * rank.getValue())
                .sum();
    }

    public double calculateProfit(int totalPrice) {
        return totalReward() / totalPrice;
    }

    public int countByRank(Rank rank) {
        return ranks.get(rank);
    }
}
