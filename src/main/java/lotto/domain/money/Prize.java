package lotto.domain.money;

import java.util.Arrays;

public enum Prize {
    FIRST(6, 2_000_000_000, false),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1500_000, false),
    FOURTH(4, 50_000, false),
    FIFTH(3, 5_000, false),
    MISS(0, 0, false);

    private static int MATCH_NUMBER_LIMIT_TO_HAVE_PRIZE = 3;
    private int matchCount;
    private long prizeMoney;
    private boolean hasBonusNumber;

    Prize(int matchCount, long prizeMoney, boolean hasBonusNumber) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.hasBonusNumber = hasBonusNumber;
    }

    public static Prize getPrizeRank(int matchCount, boolean hasBonusNumber) {
        if (matchCount < MATCH_NUMBER_LIMIT_TO_HAVE_PRIZE) {
            return MISS;
        }

        if (matchCount == THIRD.matchCount && !hasBonusNumber) {
            return THIRD;
        }
        return Arrays.stream(values()).filter(prize -> prize.matchCount == matchCount).findAny().get();
    }

    public long getPrizeMoney() {
        return prizeMoney;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
