package domain;

import java.util.Arrays;

public enum Winning {

    FIFTH(3, new Money(5000), false),
    FOURTH(4, new Money(50000), false),
    THIRD(5, new Money(1_500_000), false),
    SECOND(5, new Money(30_000_000), true),
    FIRST(6, new Money(2_000_000_000), false),
    MISS(0, new Money(0), false);
    private final int matchCount;
    private final Money winningMoney;
    private final boolean isRequireBonus;

    Winning(int matchCount, Money winningMoney, boolean isRequireBonus) {
        this.matchCount = matchCount;
        this.winningMoney = winningMoney;
        this.isRequireBonus = isRequireBonus;
    }

    public static Winning findWinning(int matchCount, boolean isRequireBonus) {
        return Arrays.stream(Winning.values())
                .filter(winning -> winning.matchCount == matchCount)
                .filter(winning -> !winning.isRequireBonus || winning.isRequireBonus == isRequireBonus)
                .findFirst().orElse(MISS);
    }
}
