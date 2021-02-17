package lotto.domain;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, false, 2000000000),
    SECOND(5, true, 30000000),
    THIRD(5, false, 1500000),
    FOURTH(4, false, 50000),
    FIFTH(3, false, 5000),
    MISS(0, false, 0);

    private final int matchCounts;
    private final boolean isBonusBall;
    private final int prizeMoney;

    LottoRank(int matchCounts, boolean isBonusBall, int prizeMoney) {
        this.matchCounts = matchCounts;
        this.isBonusBall = isBonusBall;
        this.prizeMoney = prizeMoney;
    }

    public static LottoRank of(int matchCounts, boolean isBonusBall) {
        if (isBonusBall && matchCounts == SECOND.matchCounts) {
            return SECOND;
        }
        return Arrays.stream(LottoRank.values())
                .filter(lottoRank -> lottoRank != LottoRank.SECOND)
                .filter(lottoRank -> lottoRank.matchCounts == matchCounts)
                .findFirst()
                .orElseGet(() -> MISS);
    }
}
