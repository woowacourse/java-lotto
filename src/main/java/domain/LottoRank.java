package domain;

import java.util.Arrays;
import java.util.function.Predicate;

public enum LottoRank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000);

    private static final int WINNING_MATCH_COUNT_FOR_SECOND_AND_THIRD = 5;
    private final int winningMatchCount;
    private final boolean isBonusMatch;
    private final int winningMoney;

    LottoRank(int winningMatchCount, boolean isBonusMatch, int winningMoney) {
        this.winningMatchCount = winningMatchCount;
        this.isBonusMatch = isBonusMatch;
        this.winningMoney = winningMoney;
    }

    public static LottoRank findRank(final int winningMatchCount, final boolean isBonusMatch) {
        if (isSecondRank(winningMatchCount, isBonusMatch)) {
            return SECOND;
        }
        return Arrays.stream(LottoRank.values())
                .filter(getLottoRankPredicate(winningMatchCount))
                .findFirst()
                .orElse(null);
    }

    private static Predicate<LottoRank> getLottoRankPredicate(final int winningMatchCount) {
        return result -> result.winningMatchCount == winningMatchCount && !result.isBonusMatch;
    }

    private static boolean isSecondRank(final int winningMatchCount, final boolean isBonusMatch) {
        return winningMatchCount == WINNING_MATCH_COUNT_FOR_SECOND_AND_THIRD && isBonusMatch;
    }

    public int getWinningMatchCount() {
        return winningMatchCount;
    }

    public int getWinningMoney() {
        return winningMoney;
    }
}
