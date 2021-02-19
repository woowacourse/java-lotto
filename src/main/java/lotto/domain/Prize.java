package lotto.domain;

import java.util.Arrays;

public enum Prize {
    FIRST(1, 2_000_000_000, 6, false),
    SECOND(2, 30_000_000, 5, true),
    THIRD(3, 1_500_000, 5, false),
    FOURTH(4, 50_000, 4, false),
    FIFTH(5, 5_000, 3, false),
    LOSING(-1, 0, -1, false);

    private final int rank;
    private final int prizeMoney;
    private final int matchCount;
    private final boolean isBonus;

    Prize(int rank, int prizeMoney, int matchCount, boolean isBonus) {
        this.rank = rank;
        this.prizeMoney = prizeMoney;
        this.matchCount = matchCount;
        this.isBonus = isBonus;
    }

    public static Prize findByMatchCount(int matchCount, boolean isBonus) {
        return Arrays.stream(Prize.values())
            .filter(prize -> prize.matchCount == matchCount && prize.isBonus == isBonus)
            .findFirst()
            .orElse(LOSING);
    }

    public int getRank() {
        return rank;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
