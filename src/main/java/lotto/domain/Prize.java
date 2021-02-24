package lotto.domain;

import java.util.Arrays;

public enum Prize {
    FIRST(1, 2_000_000_000, 6),
    SECOND(2, 30_000_000, 5),
    THIRD(3, 1_500_000, 5),
    FOURTH(4, 50_000, 4),
    FIFTH(5, 5_000, 3),
    LOSING(-1, 0, -1);

    private final int rank;
    private final int prizeMoney;
    private final int matchCount;

    Prize(int rank, int prizeMoney, int matchCount) {
        this.rank = rank;
        this.prizeMoney = prizeMoney;
        this.matchCount = matchCount;
    }

    public static Prize findByMatchCount(int matchCount, boolean isBonus) {
        if (matchCount == SECOND.matchCount) {
            return secondOrThird(isBonus);
        }

        return Arrays.stream(Prize.values())
                .filter(prize -> prize.matchCount == matchCount)
                .findFirst()
                .orElse(LOSING);
    }

    private static Prize secondOrThird(boolean isBonus) {
        if (isBonus) {
            return SECOND;
        }
        return THIRD;
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
