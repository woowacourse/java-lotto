package domain;

import java.util.Arrays;

public enum Rank {
    NONE(0, 0, false),
    THREE(3, 5_000, false),
    FOUR(4, 50_000, false),
    FIVE(5, 1_500_000, false),
    FIVE_WITH_BONUS(5, 30_000_000, true),
    SIX(6, 2_000_000_000, false);

    private final int matchCount;
    private final int prizeMoney;
    private final boolean matchBonus;

    Rank(int matchCount, int prizeMoney, boolean matchBonus) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.matchBonus = matchBonus;
    }

    public static Rank findByMatchCountAndBonus(int matchCountValue, boolean matchBonusValue) {
        Rank rankFilteredByMatchCount = Arrays.stream(Rank.values())
                .filter(rank -> rank.matchCount == matchCountValue)
                .findFirst()
                .orElse(NONE);

        if (rankFilteredByMatchCount == FIVE && matchBonusValue == FIVE_WITH_BONUS.matchBonus) {
            return FIVE_WITH_BONUS;
        }

        return rankFilteredByMatchCount;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
