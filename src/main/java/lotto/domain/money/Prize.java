package lotto.domain.money;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Prize {
    FIRST(6, 2000000000, false),
    SECOND(5, 30000000, true),
    THIRD(5, 1500000, false),
    FOURTH(4, 50000, false),
    FIFTH(3, 5000, false),
    MISS(0, 0, false);

    private static int MATCH_NUMBER_LIMIT_TO_HAVE_PRIZE = 3;
    private int matchCount;
    private int prizeMoney;
    private boolean hasBonusNumber;

    Prize(int matchCount, int prizeMoney, boolean hasBonusNumber) {
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

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
