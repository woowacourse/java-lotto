package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Rewards {

    private final List<Reward> rewards;

    public Rewards(final List<Reward> rewards) {
        this.rewards = new ArrayList<>(rewards);
    }

    public int getRankCount(Reward reward) {
        return (int) rewards.stream()
            .filter(value -> value == reward)
            .count();
    }

    public double profit(final int money) {
        long profit = rewards.stream()
            .mapToInt(Reward::getWinningMoney)
            .sum();

        return LottoCalculator.divide(money, profit);
    }
}
