package domain;

import java.util.Arrays;
import java.util.function.Predicate;

public enum LottoRank {
    FIRST(6, false, 2_000_000_000, "6개 일치(2000000000원) - "),
    SECOND(5, true, 30_000_000, "5개 일치, 보너스볼 일치(30000000원) - "),
    THIRD(5, false, 1_500_000, "5개 일치(1500000원) - "),
    FOURTH(4, false, 50_000, "4개 일치(50000원) - "),
    FIFTH(3, false, 5_000, "3개 일치(5000원) - ");

    private static final int WINNING_MATCH_COUNT_FOR_SECOND_AND_THIRD = 5;
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

    public static LottoRank findRank(final int winningMatchCount, final boolean isBonusMatch) {
        if (isSecondRank(winningMatchCount, isBonusMatch)){
            return SECOND;
        }
        return Arrays.stream(LottoRank.values())
                .filter(getLottoRankPredicate(winningMatchCount))
                .findFirst()
                .orElse(null);
    }

    private static Predicate<LottoRank> getLottoRankPredicate(final int winningMatchCount) {
        return result -> result.winningMatchCount == winningMatchCount;
    }

    private static boolean isSecondRank(final int winningMatchCount, final boolean isBonusMatch) {
        return winningMatchCount == WINNING_MATCH_COUNT_FOR_SECOND_AND_THIRD && isBonusMatch;
    }

    public String getResultMessage(){
        return resultMessage;
    }

    public int getWinningMoney(){
        return winningMoney;
    }
}
