package lotto.domain.ticketresult;

import java.util.Arrays;

public enum Rank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);

    private final int matchedNumberCount;
    private final boolean hasBonusNumber;
    private final int prizeMoney;

    Rank(int matchedNumberCount, boolean hasBonusNumber, int prizeMoney) {
        this.matchedNumberCount = matchedNumberCount;
        this.hasBonusNumber = hasBonusNumber;
        this.prizeMoney = prizeMoney;
    }

    public static Rank getLottoRank(int matchedNumberCount, boolean hasBonusNumber) {
        if (isSecondPrize(matchedNumberCount, hasBonusNumber)) {
            return SECOND;
        }
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.matchedNumberCount == matchedNumberCount)
                .filter(rank -> rank != SECOND)
                .findFirst()
                .orElseGet(() -> NONE);
    }

    private static boolean isSecondPrize(int matchedNumberCount, boolean hasBonusNumber) {
        return (matchedNumberCount == SECOND.matchedNumberCount) && hasBonusNumber;
    }

    public int getCountMatchedNumbers() {
        return matchedNumberCount;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }
}
