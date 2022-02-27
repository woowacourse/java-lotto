package model;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    FAILED(0, 0);

    private final int winningNumberCount;
    private final long prizeMoney;

    LottoRank(int winningNumberCount, long prizeMoney) {
        this.winningNumberCount = winningNumberCount;
        this.prizeMoney = prizeMoney;
    }

    public static LottoRank getRank(int winningNumberCount, boolean containsBonusBall) {
        if (winningNumberCount == THIRD.winningNumberCount && !containsBonusBall) {
            return THIRD;
        }

        return Arrays.stream(values())
                .filter(lottoRank -> lottoRank.winningNumberCount == winningNumberCount)
                .findFirst()
                .orElse(FAILED);
    }

    public int getWinningNumberCount() {
        return winningNumberCount;
    }

    public long getPrizeMoney() {
        return prizeMoney;
    }
}
