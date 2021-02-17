package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class GameResult {
    private final List<Rank> ranks;

    public GameResult() {
        ranks = new ArrayList<>();
    }

    public void add(Rank rank) {
        ranks.add(rank);
    }

    public int countOfLotto() {
        return ranks.size() * Money.PRICE_OF_LOTTO;
    }

    public int totalReward() {
        int sum = 0;
        for (Rank rank : ranks) {
            sum += rank.getReward();
        }
        return sum;
    }

    public double calculateProfit() {
        return (double) totalReward() / countOfLotto();
    }

    public int countByRank(Rank rank) {
        return (int) ranks.stream()
                .filter(rank::matchRank)
                .count();
    }
}
