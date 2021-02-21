package lotto.domain;

import java.util.Arrays;

public enum LottoRank {
    FIRST_PRIZE(6, false, 2_000_000_000),
    SECOND_PRIZE(5, true, 30_000_000),
    THIRD_PRIZE(5, false, 1_500_000),
    FOURTH_PRIZE(4, false, 50_000),
    FIFTH_PRIZE(3, false, 5_000),
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
        if (isSecondPrize(matchCounts, isBonusBall)) {
            return SECOND_PRIZE;
        }
        return Arrays.stream(LottoRank.values())
                .filter(lottoRank -> lottoRank != LottoRank.SECOND_PRIZE)
                .filter(lottoRank -> lottoRank.matches(matchCounts))
                .findFirst()
                .orElseGet(() -> MISS);
    }

    private static boolean isSecondPrize(int matchCounts, boolean isBonusBall) {
        return isBonusBall == SECOND_PRIZE.isBonusBall && matchCounts == SECOND_PRIZE.matchCounts;
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
