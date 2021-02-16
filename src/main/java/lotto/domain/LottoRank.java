package lotto.domain;

import java.util.Arrays;

public enum LottoRank {
    FIRST(7), SECOND(6),
    THIRD(5), FOURTH(4),
    FIFTH(3), NONE(0);

    int matchingCount;

    LottoRank(int matchingCount) {
        this.matchingCount = matchingCount;
    }

    public static LottoRank getRank(int matchingCount) {
        return Arrays.stream(values())
            .filter(rank -> rank.isSameMatchingCount(matchingCount))
            .findAny()
            .orElse(NONE);
    }

    private boolean isSameMatchingCount(int matchingCount) {
        return this.matchingCount == matchingCount;
    }
}