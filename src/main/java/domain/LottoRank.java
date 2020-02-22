package domain;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, false, 2_000_000_000, "6개 일치(2000000000원) - "),
    SECOND(5, true, 30_000_000, "5개 일치, 보너스볼 일치(30000000원) - "),
    THIRD(5, false, 1_500_000, "5개 일치(1500000원) - "),
    FOURTH(4, false, 50_000, "4개 일치(50000원) - "),
    FIFTH(3, false, 5_000, "3개 일치(5000원) - ");

    public static final int WINNING_MATCH_COUNT_FOR_SECOND_AND_THIRD = 5;
    private int winningMatchCount;
    private boolean isBonusMatch;
    private int winningMoney;
    private String resultMessage;

    LottoRank(int winningMatchCount, boolean isBonusMatch, int winningMoney, String resultMessage){
        this.winningMatchCount = winningMatchCount;
        this.isBonusMatch = isBonusMatch;
        this.winningMoney = winningMoney;
        this.resultMessage = resultMessage;
    }

    public static LottoRank findRank(final int winningMatchCount, final boolean bonusMatchCount) {
            return Arrays.stream(LottoRank.values())
                    .filter(result -> isRankSecondOrThird(bonusMatchCount, result) || isRankOneOrFourthOrFifth(result))
                    .filter(result -> isWinningMatchCountSame(winningMatchCount, result))
                    .findFirst()
                    .orElse(null);
    }

    private static boolean isWinningMatchCountSame(int winningMatchCount, LottoRank result) {
        return result.winningMatchCount == winningMatchCount;
    }

    private static boolean isRankOneOrFourthOrFifth(LottoRank result) {
        return !result.isBonusMatch;
    }

    private static boolean isRankSecondOrThird(boolean bonusMatchCount, LottoRank result) {
        return isWinningMatchCountSame(WINNING_MATCH_COUNT_FOR_SECOND_AND_THIRD, result) && result.isBonusMatch == bonusMatchCount;
    }

    public String getResultMessage(){
        return resultMessage;
    }

    public int getWinningMoney(){
        return winningMoney;
    }
}
