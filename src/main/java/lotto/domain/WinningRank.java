package lotto.domain;

import java.util.Arrays;

public enum WinningRank {
    FIFTH_RANK(5_000, 3, false),
    FOURTH_RANK(50_000, 4, true),
    THIRD_RANK(1_500_000, 5, false),
    SECOND_RANK(30_000_000, 5, false),
    FIRST_RANK(2_000_000_000, 6, false),
    NO_RANK(0, 0, false);

    private final int winningMoney;
    private final int winningBallCount;
    private boolean isBonusBall;

    private WinningRank(int winningMoney, int winningBallCount, boolean isBonusBall) {
        this.winningMoney = winningMoney;
        this.winningBallCount = winningBallCount;
        this.isBonusBall = isBonusBall;
    }

    public static WinningRank determineRank(int correctNumber, boolean isBonusNumber) {
        return Arrays.stream(values())
                .filter(result -> result.winningBallCount == correctNumber && result.isBonusBall == isBonusNumber)
                .findFirst()
                .orElse(NO_RANK);

    }


    public int getWinningMoney() {
        return winningMoney;
    }

    public int getWinningBallCount() {
        return winningBallCount;
    }

    public boolean isBonusBall() {
        return isBonusBall;
    }
}
