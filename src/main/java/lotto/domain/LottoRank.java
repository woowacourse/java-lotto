package lotto.domain;

import java.util.Arrays;

public enum LottoRank {
    FIFTH(3, 5_000), FOURTH(4, 50_000),
    THIRD(5, 1_500_000), SECOND(5.5, 30_000_000),
    FIRST(6, 200_000_000), NONE(0, 0);

    double matchingCount;
    int prizeMoney;

    LottoRank(double matchingCount, int prizeMoney) {
        this.matchingCount = matchingCount;
        this.prizeMoney = prizeMoney;
    }

    public static LottoRank getRank(double matchingCount) {
        return Arrays.stream(values())
            .filter(element -> element.isSameMatchingCount(matchingCount))
            .findAny()
            .orElse(NONE);
    }

    private boolean isSameMatchingCount(double matchingCount) {
        return this.matchingCount == matchingCount;
    }

    public int getPrizeMoney() {
        return this.prizeMoney;
    }

    public double getMatchingCount() {
        return this.matchingCount;
    }
}