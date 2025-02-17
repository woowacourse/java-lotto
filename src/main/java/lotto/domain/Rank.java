package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIFTH(5_000, 3, false),
    FOURTH(50_000, 4, false),
    THIRD(1_500_000, 5, false),
    SECOND(30_000_000, 5, true),
    FIRST(2_000_000_000, 6, false),
    NONE(0,0, false);

    private final int money;
    private final int matchCounts;
    private final boolean matchBonus;

    Rank(int money, int matchCounts, boolean matchBonus) {
        this.money = money;
        this.matchCounts = matchCounts;
        this.matchBonus = matchBonus;
    }

    public static Rank checkRank(int matchCounts, boolean matchBonus) {
        if (matchCounts == 5) {
            return checkEqualFive(matchBonus);
        }
        return Arrays.stream(Rank.values()).toList()
                .stream()
                .filter(r -> r.matchCounts == matchCounts)
                .findAny()
                .orElse(Rank.NONE);
    }

    public int calculateTotalProfit(int winningCounts) {
        return winningCounts * this.money;
    }

    private static Rank checkEqualFive(boolean matchBonus) {
        if (matchBonus == SECOND.matchBonus) {
            return SECOND;
        }
        return THIRD;
    }

    public int getMoney() {
        return money;
    }

    public int getMatchCounts() {
        return matchCounts;
    }

    public boolean isMatchBonus() {
        return matchBonus;
    }
}
