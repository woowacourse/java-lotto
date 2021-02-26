package lotto.domain;

import java.util.*;

public class LottoGameResult {

    private final Map<Rank, Integer> resultMap;

    public LottoGameResult() {
        resultMap = new EnumMap<>(Rank.class);
        initEnumMap();
    }

    private void initEnumMap() {
        Arrays.stream(Rank.values())
                .forEach(rank -> resultMap.put(rank, 0));
    }

    public void add(List<Rank> matchRank) {
        matchRank.forEach(rank -> resultMap.put(rank, resultMap.get(rank) + 1));
    }

    public Map<Rank, Integer> toResultMap() {
        return Collections.unmodifiableMap(resultMap);
    }

    public int countByRank(Rank rank) {
        return resultMap.get(rank);
    }

    public double calculateProfit() {

        return totalReward() / calculateTotalPrice();
    }

    private double totalReward() {

        return resultMap.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getReward() * entry.getValue())
                .sum();
    }

    private double calculateTotalPrice() {
        double numberOfTotalLotto = resultMap.values()
                .stream()
                .mapToDouble(num -> num)
                .sum();

        return numberOfTotalLotto * Money.PRICE_OF_LOTTO;
    }
}
