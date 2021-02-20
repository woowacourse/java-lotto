package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LottoGameResult {
    private final Map<Rank, Integer> resultMap;

    public LottoGameResult() {
        resultMap = new HashMap<>();
        initEnumMap();
    }

    private void initEnumMap() {
        Arrays.stream(Rank.values())
                .forEach(rank -> resultMap.put(rank, 0));
    }

    public void add(Rank rank) {
        resultMap.put(rank, resultMap.get(rank) + 1);
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

    private int calculateTotalPrice() {
        int numberOfTotalLotto = resultMap.values()
                .stream()
                .mapToInt(num -> num)
                .sum();

        return numberOfTotalLotto * Money.PRICE_OF_LOTTO;
    }
}
