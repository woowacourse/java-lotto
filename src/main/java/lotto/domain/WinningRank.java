package lotto.domain;

import java.util.Arrays;

public enum WinningRank {
    FIRST_RANK(2_000_000_000,6)
    ,SECOND_RANK(30_000_000,5)
    , THIRD_RANK(1_500_000,5)
    , FOURTH_RANK(50_000,4)
    , FIFTH_RANK(5_000,3),
    ,NO_RANK(0,0);

    private final int winningMoney;
    private final int winningBallCount;

    private WinningRank(int winningMoney, int winningBallCount){
        this.winningMoney = winningMoney;
        this.winningBallCount = winningBallCount;
    }

    public static WinningRank selectRank(int correctNumber,boolean isBonusNumber){
        WinningRank winningRank = Arrays.stream(values())
                .filter(result -> result.winningBallCount == correctNumber)
                .findFirst()
                .orElse(NO_RANK);

        return compareSecondRankOrThirdRank(isBonusNumber, winningRank);
    }

    private static WinningRank compareSecondRankOrThirdRank(boolean isBonusNumber, WinningRank winningRank) {
        if (winningRank == SECOND_RANK && !isBonusNumber){
            return THIRD_RANK;
        }
        return winningRank;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public int getWinningBallCount() {
        return winningBallCount;
    }
}
