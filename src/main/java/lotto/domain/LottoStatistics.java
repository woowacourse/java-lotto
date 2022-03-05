package lotto.domain;

import static lotto.domain.LottoGame.LOTTO_PRICE;

import java.util.EnumMap;
import java.util.List;

public class LottoStatistics {

    private static final int NON_COUNT = 0;
    private static final int COUNT = 1;

    private final EnumMap<Rank, Integer> statistics;
    private final Money reward;

    public LottoStatistics(List<Rank> ranks) {
        this.statistics = initializeState();
        calculateStatistics(ranks);
        this.reward = createReward();
    }

    public double getYield() {
        int investMoney = statistics.values().stream()
                .reduce(0, Integer::sum)
                * LOTTO_PRICE;
        return reward.divideBy(investMoney);
    }

    private EnumMap<Rank, Integer> initializeState() {
        EnumMap<Rank, Integer> temp = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            temp.put(rank, NON_COUNT);
        }
        return temp;
    }

    private void calculateStatistics(List<Rank> ranks) {
        for (Rank rank : ranks) {
            statistics.merge(rank, COUNT, Integer::sum);
        }
    }

    private Money createReward() {
        int totalReward = statistics.keySet().stream()
                .mapToInt(rank -> rank.calculateTotalReward(statistics.get(rank)))
                .sum();
        return new Money(totalReward);
    }

    public EnumMap<Rank, Integer> getStatistics() {
        return new EnumMap<>(statistics);
    }
}
