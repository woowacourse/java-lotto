package lotto.domain;

import java.util.Arrays;

public enum LottoRank {
    FIRST(7, 200_000_000), SECOND(6, 30_000_000),
    THIRD(5, 1_500_000), FOURTH(4, 50_000),
    FIFTH(3, 5_000), NONE(0, 0);

    int matchingCount;
    int prizeMoney;

    LottoRank(int matchingCount, int prizeMoney) {
        this.matchingCount = matchingCount;
        this.prizeMoney = prizeMoney;
    }

    public static LottoRank getRank(int matchingCount) {
        return Arrays.stream(values())
            .filter(element -> element.isSameMatchingCount(matchingCount))
            .findAny()
            .orElse(NONE);
    }

    public static int tranlateRankToPrizeMoney(String rank) {
        LottoRank lottoRank = Arrays.stream(values())
            .filter(element -> element.isSameMatchingRank(rank))
            .findAny()
            .orElseThrow(RuntimeException::new);
        return lottoRank.prizeMoney;
    }

    private boolean isSameMatchingCount(int matchingCount) {
        return this.matchingCount == matchingCount;
    }

    private boolean isSameMatchingRank(String rank) {
        return this.name().equals(rank);
    }
}