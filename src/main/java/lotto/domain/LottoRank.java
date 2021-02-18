package lotto.domain;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, 2000000000),
    SECOND(5, 30000000),
    THIRD(5, 1500000),
    FOURTH(4,  50000),
    FIFTH(3,  5000),
    MISS(0,  0);

    private final int matchCounts;
    private final int prizeMoney;

    LottoRank(int matchCounts, int prizeMoney) {
        this.matchCounts = matchCounts;
        this.prizeMoney = prizeMoney;
    }

    public static LottoRank of(int matchCounts, boolean isBonusBall) {
        if (isSecond(matchCounts, isBonusBall)) {
            return SECOND;
        }
        return Arrays.stream(LottoRank.values())
                .filter(lottoRank -> lottoRank != LottoRank.SECOND)
                .filter(lottoRank -> lottoRank.matches(matchCounts))
                .findFirst()
                .orElseGet(() -> MISS);
    }

    private static boolean isSecond(int matchCounts, boolean isBonusBall) {
        return isBonusBall && matchCounts == SECOND.matchCounts;
    }

    private boolean matches(int matchCounts) {
        return this.matchCounts == matchCounts;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public int getMatchCounts() {
        return matchCounts;
    }
}
