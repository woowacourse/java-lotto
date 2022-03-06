package domain;

import java.util.Arrays;

public enum WinningPrize {
    FIRST(2_000_000_000, 6),
    SECOND(30_000_000, 5),
    THIRD(1_500_000, 5),
    FOURTH(50_000, 4),
    FIFTH(5_000, 3),
    NONE(0, 0);

    private final int prizeMoney;
    private final int matchCount;

    WinningPrize(int prizeMoney, int matchCount) {
        this.prizeMoney = prizeMoney;
        this.matchCount = matchCount;
    }

    public static WinningPrize findWinningPrize(int matchCount, boolean matchBonus) {
        if (matchCount == SECOND.matchCount && matchBonus) {
            return SECOND;
        }
        return findDetailPrize(matchCount);
    }

    private static WinningPrize findDetailPrize(int matchCount) {
        return Arrays.stream(values())
                .filter(winningPrize -> matchCount == winningPrize.matchCount && winningPrize != SECOND)
                .findFirst()
                .orElse(NONE);
    }

    public boolean isMatchBonus() {
        return this == SECOND;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
