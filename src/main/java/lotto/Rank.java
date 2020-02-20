package lotto;

import java.util.Arrays;
import java.util.List;

public enum Rank {
    FIFTH(3, 5000),
    FOURTH(4, 50000),
    THIRD(5, 1500000),
    SECOND(5, 30000000),
    FIRST(6, 2000000000);

    private int matchedCount;
    private int winningMoney;

    Rank(int matchedCount, int winningMoney) {
        this.matchedCount = matchedCount;
        this.winningMoney = winningMoney;
    }

    public static Rank of(int count) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchedCount == count)
                .findFirst()
                .orElse(null);
    }

    public static double sumWinningMoney(List<Rank> ranks) {
        return ranks.stream()
                .mapToDouble(rank -> rank.winningMoney)
                .sum();
    }

    public int getMatchedCount() {
        return matchedCount;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public int getContainingCount(List<Rank> ranks) {
        return (int)ranks.stream()
                .filter(rank -> rank.equals(this))
                .count();
    }
}
