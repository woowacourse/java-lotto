package lotto.domain;

import java.util.Arrays;
import java.util.List;

public enum Rank {
    FIFTH(3, 5000),
    FOURTH(4, 50000),
    THIRD(5, 1500000),
    SECOND(5, 30000000),
    FIRST(6, 2000000000);

    private static final int DEFAULT_MONEY = 0;
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
                .orElse(null);
    }

    public static Money sumWinningMoney(List<Rank> ranks) {
        Money totalWinningMoney = Money.of(DEFAULT_MONEY);
        for (Rank rank : ranks) {
            totalWinningMoney = totalWinningMoney.plus(rank.getWinningMoney());
        }
        return totalWinningMoney;
    }

    public int getContainingCount(List<Rank> ranks) {
        return (int)ranks.stream()
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
