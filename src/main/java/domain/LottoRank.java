package domain;

import java.util.Arrays;
import java.util.function.Predicate;

public enum LottoRank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);

    private final int winningMatchCount;
    private final boolean isBooleanMatch;
    private final int winningMoney;

    LottoRank(int winningMatchCount, boolean isBooleanMatch, int winningMoney) {
        this.winningMatchCount = winningMatchCount;
        this.isBooleanMatch = isBooleanMatch;
        this.winningMoney = winningMoney;
    }

    public static LottoRank findRank(final int winningMatchCount, final boolean isBooleanMatch) {
        return Arrays.stream(LottoRank.values())
                .filter(getLottoRankPredicate(winningMatchCount, isBooleanMatch))
                .findFirst()
                .orElse(NONE);
    }

    private static Predicate<LottoRank> getLottoRankPredicate(int winningMatchCount, boolean isBooleanMatch) {
        return result -> result.winningMatchCount == winningMatchCount && result.isBooleanMatch == isBooleanMatch;
    }


    public int getWinningMatchCount() {
        return winningMatchCount;
    }

    public int getWinningMoney() {
        return winningMoney;
    }
}
