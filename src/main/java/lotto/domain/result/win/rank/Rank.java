package lotto.domain.result.win.rank;

import lotto.domain.result.MatchResult;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    SIXTH(0, 0);

    private final int matchCount;
    private final int defaultPrize;

    Rank(int matchCount, int defaultPrize) {
        this.matchCount = matchCount;
        this.defaultPrize = defaultPrize;
    }

    public static Rank findPrizeByLottoResult(MatchResult result) {
        Rank rank = Arrays.stream(Rank.values())
                .filter(aRank -> result.isEqualToMatchCount(aRank.matchCount))
                .findFirst()
                .orElse(SIXTH);

        if (rank.isThird(result)) {
            return THIRD;
        }
        return rank;
    }

    private boolean isThird(MatchResult matchResult) {
        return this == SECOND && !matchResult.isBonusMatch();
    }

    public int getDefaultPrize() {
        return defaultPrize;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
