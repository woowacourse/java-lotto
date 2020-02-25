package lotto.domain;

import java.util.Arrays;
import java.util.List;

public enum Rank {
    LOSE(-1, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000);

    private static final int DEFAULT_SUM = 0;
    private int matchedCount;
    private Money winningMoney;

    Rank(int matchedCount, int winningMoney) {
        this.matchedCount = matchedCount;
        this.winningMoney = Money.of(winningMoney);
    }

    public static Rank of(int count) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchedCount == count)
                .findFirst()
                .orElse(LOSE);
    }

    public boolean isValidRank() {
        return this != LOSE;
    }

    public static Money sumWinningMoney(List<Rank> ranks) {
        Money totalWinningMoney = Money.of(DEFAULT_SUM);
        for (Rank rank : ranks) {
            totalWinningMoney = totalWinningMoney.plus(rank.winningMoney);
        }
        return totalWinningMoney;
    }

    public int getContainingCount(List<Rank> ranks) {
        return (int) ranks.stream()
                .filter(rank -> rank.equals(this))
                .count();
    }

    public int getMatchedCount() {
        return matchedCount;
    }

    public Money getWinningMoney() {
        return winningMoney;
    }
}