package lotto.domain;

import java.util.Arrays;

public enum WinningRank {
    FIFTH_RANK(5_000, 3, false),
    FOURTH_RANK(50_000, 4, false),
    THIRD_RANK(1_500_000, 5, false),
    SECOND_RANK(30_000_000, 5, true),
    FIRST_RANK(2_000_000_000, 6, false),
    NO_RANK(0, 0, false);

    private final long winningMoney;
    private final int winningBallCount;
    private boolean isBonusBall;

    private WinningRank(long winningMoney, int winningBallCount, boolean isBonusBall) {
        this.winningMoney = winningMoney;
        this.winningBallCount = winningBallCount;
        this.isBonusBall = isBonusBall;
    }

    public static WinningRank determineRank(int correctNumber, boolean isBonusNumber) {
        return Arrays.stream(values())
                .filter(result -> findRank(correctNumber, result)
                        && determineSecondAndThird(isBonusNumber, result))
                .findFirst()
                .orElse(NO_RANK);

    }

    private static boolean findRank(int correctNumber, WinningRank result) {
        return result.winningBallCount == correctNumber;
    }

    private static boolean determineSecondAndThird(boolean isBonusNumber, WinningRank result) {
        return result.isBonusBall == isBonusNumber;
    }


    public long getWinningMoney() {
        return winningMoney;
    }

    public int getWinningBallCount() {
        return winningBallCount;
    }
}
