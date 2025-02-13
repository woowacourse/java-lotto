package domain;

import java.util.Arrays;

public enum Rank {

    FIFTH(3, new Money(5000), false),
    FOURTH(4, new Money(50000), false),
    THIRD(5, new Money(1_500_000), false),
    SECOND(5, new Money(30_000_000), true),
    FIRST(6, new Money(2_000_000_000), false),
    MISS(0, new Money(0), false);
    private final int matchCount;
    private final Money winningMoney;
    private final boolean isRequireBonus;

    Rank(int matchCount, Money winningMoney, boolean isRequireBonus) {
        this.matchCount = matchCount;
        this.winningMoney = winningMoney;
        this.isRequireBonus = isRequireBonus;
    }

    public static Rank findRank(int matchCount, boolean isRequireBonus) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.matchCount == matchCount)
                .filter(rank -> {
                    if (rank.matchCount == 5) {
                        return rank.isRequireBonus == isRequireBonus;
                    }
                    return true;
                }).findFirst().orElse(MISS);
    }

    public Money calculateWinningMoney(int count) {
        return winningMoney.multiply(count);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public Money getWinningMoney() {
        return winningMoney;
    }

    public boolean isRequireBonus() {
        return isRequireBonus;
    }
}
